package com.henriques.task_manager.controller;

import com.henriques.task_manager.api.TaskDto;
import com.henriques.task_manager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller("/")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("index");
        List<TaskDto> taskDtoList = taskService.getAllTasks();
        mv.addObject("taskDtoList", taskDtoList);
        return mv;
    }

    @GetMapping("/add-new-task")
    public ModelAndView addNewTask() {
        ModelAndView mv = new ModelAndView("new-task");
        mv.addObject("taskDto", new TaskDto());

        return mv;
    }
}
