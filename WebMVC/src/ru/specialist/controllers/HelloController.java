package ru.specialist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.specialist.model.UserVM;

// Диспетчер-сервлет для вызова action будет искать только бины,
// отмеченные аннотацией @Controller
// Диспетчер-сервлет использует @RequestMapping для подсказки,
// когда следует использовать этот action. Параметр - кусок адреса URL
// localhost/MVC/hello - виртуальный адрес, который мэпим на контроллер
@Controller
@RequestMapping("/hello")
public class HelloController {

    private UserVM userVM;

    public UserVM getUserVM()
    {
        return userVM;
    }

    // связываем контроллер с моделью userVM.
    // за создание и инициализацию модели отвечает контейнер Spring
    @Autowired
    public void setUserVM(UserVM userVM)
    {
        this.userVM = userVM;
    }

    // Action №1 - открыть страницу с формой
    // параметр @RequestMapping - метод команды http: /hello GET
    // параметром action'а является объект uiModel —
    // модель данных, которая будет передана движку рендеринга
    // для параметризации представления
    @RequestMapping(method = RequestMethod.GET)
    public String showForm(Model uiModel) {
        // добавляем в uiModel ссылку на userVM под именем user,
        // которую заинжектил контейнер в контроллер
        uiModel.addAttribute("user", userVM);
        // action возвращает строку, кусок имени представления,
        // далее эта строка будет передана движку рендеринга,
        // сконфигурированного в servlet-context.xml,
        // с добавлением префикса и суффикса
        return "hello/form";
    }

    // Action №2 - обработка нажатия на кнопку
    // параметр @RequestMapping - метод команды http: /hello POST
    // будет обработан после отправки в http метода POST form.jsp
    // в качестве параметров: uiModel (см. выше) и
    // userVM, которая пришла от клиента
    // bindingResult — для обработки ошибок связывания
    @RequestMapping(method = RequestMethod.POST)
    public String showHello(UserVM userVM,  BindingResult bindingResult,
                            Model uiModel) {
        /*
        if (bindingResult.hasErrors()) {
            userVM.setMessageSource(this.userVM.getMessageSource());
        }
        */
        this.userVM.setUserName(userVM.getUserName());

        // добавляем в uiModel ссылку, полученную от пользователя
        uiModel.addAttribute("user", this.userVM);
        return "hello/form";
    }
}
