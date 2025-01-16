package com.github.chizzaru.controller;

import com.github.chizzaru.beans.Emp;
import com.github.chizzaru.dao.EmpDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class EmpController {

    private final EmpDao empDao;

    public EmpController(EmpDao empDao) {
        this.empDao = empDao;
    }

    @RequestMapping("/empform")
    public String showform(Model m){
        m.addAttribute("command",new Emp());
        return "empform";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("emp") Emp emp){
        empDao.save(emp);
        return "redirect:/viewemp";
    }

    @RequestMapping("/viewemp")
    public String viewemp(Model m){
        List<Emp> list = empDao.getEmployees();
        m.addAttribute("list",list);
        return "viewemp";
    }

    @RequestMapping(value = "/editemp/{id}")
    public String edit(@PathVariable("id") int id, Model m){
        Emp emp = empDao.getEmpById(id);
        m.addAttribute("command",emp);
        return "empeditform";
    }

    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
    public String editsave(@ModelAttribute("emp") Emp emp){
        empDao.update(emp);
        return "redirect:/viewemp";
    }

    @RequestMapping(value = "/deleteemp/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") int id){
        empDao.delete(id);
        return "redirect:/viewemp";
    }


}
