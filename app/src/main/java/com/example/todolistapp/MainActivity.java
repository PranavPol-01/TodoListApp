package com.example.todolistapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TaskAdapter.OnTaskClickListener {
    private TaskDbHelper dbHelper;
    private List<Task> tasks;
    private TaskAdapter adapter;
    private EditText etNewTask;
    private Button btnAddTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new TaskDbHelper(this);
        tasks = dbHelper.getAllTasks();

        etNewTask = findViewById(R.id.etNewTask);
        btnAddTask = findViewById(R.id.btnAddTask);

        RecyclerView rvTasks = findViewById(R.id.rvTasks);
        rvTasks.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TaskAdapter(tasks, this);
        rvTasks.setAdapter(adapter);

        btnAddTask.setOnClickListener(v -> addTask());
    }

    private void addTask() {
        String taskName = etNewTask.getText().toString();
        if (!taskName.isEmpty()) {
            Task newTask = new Task(taskName);
            dbHelper.addTask(newTask);
            tasks.clear();
            tasks.addAll(dbHelper.getAllTasks());
            adapter.notifyDataSetChanged();
            etNewTask.setText("");
        }
    }

    @Override
    public void onTaskClick(int position) {
        Task task = tasks.get(position);
        dbHelper.updateTask(task);
        tasks.clear();
        tasks.addAll(dbHelper.getAllTasks());
        adapter.notifyItemChanged(position);
    }

    @Override
    public void onDeleteClick(int position) {
        Task task = tasks.get(position);
        dbHelper.deleteTask(task.getId());
        tasks.remove(position);
        adapter.notifyItemRemoved(position);
    }
}
