package br.edu.ifpr.task_manager.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifpr.task_manager.enums.TaskStatus;
import br.edu.ifpr.task_manager.models.Task;

@Controller
@RequestMapping({ "", "/tasks" })
public class TaskController {

    private List<Task> tasks = new ArrayList<>();

    public TaskController (){
        Task t1 = new Task("Task1", "T1 descrição", LocalDate.now());
        Task t2 = new Task("Task2", "T2 descrição", LocalDate.now());
        Task t3 = new Task("Task3", "T3 descrição", LocalDate.now());

        tasks.add(t1);
        tasks.add(t2);
        tasks.add(t3);
    }

    @GetMapping({ "", "/", "/tasks" })
    public String listTask(Model model) {

        for (Task t : tasks) {
            System.err.println(t.getTitulo());
        }

        model.addAttribute("tasksList", tasks);

        return "task-list";

    }

    @GetMapping("/create")
    public String createTask() {

        return "task-create";
    }

    @PostMapping("/create")
    public String saveTask(Task task) {

        tasks.add(task);

        return "redirect:/tasks";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable UUID id, Model model) {

        for (Task task : tasks) {

            if (task.getId().equals(id)) {
                model.addAttribute("task", task);
                return "task-edit";
            }

        }

        return "redirect:/tasks";
    }

    @PostMapping("/edit")
    public String editTask(Task taskForm) {

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId().equals(taskForm.getId())) {
                tasks.set(i, taskForm);
                break;
            }
        }

        return "redirect:/tasks";
    }

    @GetMapping("/remove/{id}")
    public String removeTask(@PathVariable UUID id) {

        tasks.removeIf(task -> task.getId().equals(id));

        return "redirect:/tasks";
    }

    @GetMapping("/status/{id}")
    public String changeStatus(@PathVariable UUID id) {

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId().equals(id)) {
                tasks.get(i).setEstado(tasks.get(i).getEstado().equals(TaskStatus.IN_PROGRESS) ? TaskStatus.COMPLETED : TaskStatus.IN_PROGRESS);

                break;
            }
        }

        // for (Task task : tasks) {
        //     if (task.getId().equals(id)) {
        //         task.setEstado(task.getEstado().equals(TaskStatus.IN_PROGRESS) ? TaskStatus.COMPLETED : TaskStatus.IN_PROGRESS);
        //         break;
        //     }
        // }

        // tasks.stream()
        //     .filter(task -> task.getId().equals(id))
        //     .findFirst()
        //     .ifPresent(task -> task.setEstado(
        //         task.getEstado() == TaskStatus.IN_PROGRESS 
        //             ? TaskStatus.COMPLETED 
        //             : TaskStatus.IN_PROGRESS
        //     ));


        return "redirect:/tasks";
    }

    
}
