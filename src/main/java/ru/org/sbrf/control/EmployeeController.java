package ru.org.sbrf.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.org.sbrf.dao.EmployeeDao;
import ru.org.sbrf.dto.Employee;
import ru.org.sbrf.dto.Function;
import ru.org.sbrf.exception.AddObjectException;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeDao employeeDao = new EmployeeDao();

    @GetMapping({" /", ""})
    public String employeeIndex(Model model) {
        model.addAttribute("employees", employeeDao.getAll());
        return "employee";
    }

    @GetMapping("/add")
    public String add(Model model) {
        List<Function> functions = employeeDao.getFunctions();
        model.addAttribute("functions", functions);
        model.addAttribute("employee", new Employee());
        return "add";
    }

    @PostMapping("/add")
    public String addEmployee(@ModelAttribute Employee employee) {
        try {
            employeeDao.add(employee);
        } catch (AddObjectException exception) {
            exception.printStackTrace();
        }

        return "showall";
    }

    @GetMapping("/showall")
    public String showAll(Model model) {
        List<Employee> employees = employeeDao.getAll();
        model.addAttribute("employees", employees);
        return "showall";
    }

    @GetMapping("/delete/{employeeId}")
    public String delete(@PathVariable("employeeId") String employeeId, Model model) {
        employeeDao.delete(Long.parseLong(employeeId));
        model.addAttribute("employees", employeeDao.getAll());
        return "showall";
    }

    @GetMapping("/update/{employeeId}")
    public String update(@PathVariable("employeeId") String employeeId, Model model) {
        List<Function> functions = employeeDao.getFunctions();
        Employee employee = employeeDao.get(Long.parseLong(employeeId));

        model.addAttribute("employee", employee);
        model.addAttribute("functions", functions);
        model.addAttribute("employeeFunctionId", employee.getFunction().getId());
        return "update";
    }

    @PostMapping("/update")
    public String updateEmployee(@ModelAttribute Employee employee, Model model) {
        employeeDao.update(employee);

        model.addAttribute("employees", employeeDao.getAll());
        return "showall";
    }
}
