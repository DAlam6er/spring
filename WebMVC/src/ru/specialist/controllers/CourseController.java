package ru.specialist.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.validation.BindingResult;


import ru.specialist.DAO.CourseService;
import ru.specialist.DAO.Course;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/courses/")
public class CourseController
{
    private final Logger logger =
        LoggerFactory.getLogger(CourseController.class);

    private CourseService courseService;

    private MessageSource messageSource;

    @Autowired
    public void setCourseService(CourseService courseService)
    {
        this.courseService= courseService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel)
    {
        logger.info("Listing courses");
        // коллекцию курсов берём из сервиса, который заинжектил контейнер Spring
        List<Course> courses = courseService.findAll();
        // модифицируем модель
        uiModel.addAttribute ("courses", courses);
        logger.info("No. of courses: " + courses.size());
        // возвращаем имя представления
        return "courses/list";
    }

    //@PreAuthorize("isAuthenticated()")

    // /courses/delete/5 - id курса, который будет удаляться
    // @PathVariable - связывает id, указанный в @RequestMapping с параметром id в delete
    //@PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "delete/{id}",method = RequestMethod.GET) // /courses/delete/5
    public String delete(@PathVariable("id") int id, Model uiModel)
    {
        if (courseService.findById(id) != null) {
            courseService.delete(id);
        }
        logger.info("Delete course: " + id);
        // отобразить список курсов уже без удаленного курса
        // делаем редирект по указанному после двоеточия пути
        return "redirect:/courses/";
    }

    // Action №1 для update - загрузить страницу с информацией о курсе
    @RequestMapping(value = "update/{id}",method = RequestMethod.GET) // /courses/update/2
    public String updateForm(@PathVariable("id") int id, Model uiModel)
    {
        uiModel.addAttribute ("course", courseService.findById(id));
        return "courses/edit";
    }

    // Action для добавления нового курса
    @RequestMapping(value = "update/0",method = RequestMethod.GET)
    public String newForm( Model uiModel) {
        return "courses/edit";
    }

    // Пример новостного сайта
    // /news/2021/08/20
    // @RequestMapping("/news/{year}/{month}/{date}")
    // showNews(@PathVariable("year")int year, @PathVariable("month")int month)

    // Action №2 для update - сохранить изменения
    //@PreAuthorize("isAuthenticated()")
    //@PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "update/{id}", method = RequestMethod.POST)
    public String update(Course course, BindingResult bindingResult,
                         Model uiModel, HttpServletRequest httpServletRequest,
                         RedirectAttributes redirectAttributes)
    {
        logger.info("Updating contact");
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("course", course);
            uiModel.addAttribute("error", "Invalid data");
            System.out.printf("Произошла ошибка связывания: %s %s\n",
                uiModel.getAttribute("course"),
                uiModel.getAttribute("error"));
            return "courses/edit";
        }
        courseService.save(course);
        return "redirect:/courses/";
    }

}
