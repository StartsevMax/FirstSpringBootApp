package main;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class DefaultController {
    @RequestMapping("/")
    public String index() {
        return (new Date()).toString();
    }

    @GetMapping("/hello")
    public String sayHello() { return "Hello World!"; }

    @PostMapping(value = "/list")
    public ResponseEntity post(@RequestBody JsonNode jsonNode) {
        String description = jsonNode.get("description").toString();
        description = description.substring(1,description.length() - 1);
        int id = ToDoList.setTask(description);
        return new ResponseEntity(ToDoList.getTask(id),HttpStatus.OK);
    }

    @GetMapping("/list")
    public List<Task> list() {
        return ToDoList.getAllTasks();
    }

    @GetMapping("/list/{id}")
    public ResponseEntity get(@PathVariable int id) {
        Task task = ToDoList.getTask(id);
        if (task == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(task, HttpStatus.OK);
    }

    @PutMapping("/list/{id}")
    public ResponseEntity put(@PathVariable int id, @RequestBody JsonNode jsonNode) {
        String description = jsonNode.get("description").toString();
        description = description.substring(1,description.length() - 1);
        Task task = ToDoList.getTask(id);
        task.setDescription(description);
        return new ResponseEntity(task,HttpStatus.OK);
    }

    @DeleteMapping("/list/{id}")
    public void delete(@PathVariable int id) {
        ToDoList.deleteTask(id);
    }
}
