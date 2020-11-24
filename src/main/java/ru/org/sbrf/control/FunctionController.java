package ru.org.sbrf.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.org.sbrf.dao.FunctionDao;
import ru.org.sbrf.dto.Function;
import ru.org.sbrf.exception.AddObjectException;

import java.util.List;

@Controller
@RequestMapping("/function")
public class FunctionController {

    private FunctionDao functionDao = new FunctionDao();

    @GetMapping("")
    public String index(Model model) {
        List<Function> functions = functionDao.getAll();
        model.addAttribute("functions", functions);
        return "functions";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("function", new Function());
        return "add_function";
    }

    @PostMapping("/add")
    public String addFunction(@ModelAttribute Function function, Model model) {
        try {
            functionDao.add(function);
        } catch (AddObjectException exception) {
            exception.printStackTrace();
        }

        List<Function> functions = functionDao.getAll();
        model.addAttribute("functions", functions);
        return "functions";
    }

    @GetMapping("/update/{functionId}")
    public String update(@PathVariable() String functionId, Model model) {
        Function function = functionDao.get(Long.parseLong(functionId));
        model.addAttribute("function", function);

        return "update_function";
    }

    @PostMapping("/update")
    public String updateFunction(@ModelAttribute Function function, Model model) {
        functionDao.update(function);

        List<Function> functions = functionDao.getAll();
        model.addAttribute("functions", functions);
        return "functions";
    }

    @GetMapping("/delete/{functionId}")
    public String delete(@PathVariable String functionId, Model model) {
        functionDao.delete(Long.parseLong(functionId));

        List<Function> functions = functionDao.getAll();
        model.addAttribute("functions", functions);
        return "functions";
    }
}
