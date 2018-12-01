package com.example.pitts.ife.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pitts.ife.Bean.Task;
import com.example.pitts.ife.R;
import com.example.pitts.ife.Activity.TaskActivity;
import com.example.pitts.ife.Tools.RecyclerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class TaskFragment extends Fragment {

    private RecyclerView mTaskView;

    private TaskAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_task,container,false);

        mTaskView = (RecyclerView)v.findViewById(R.id.task_view);
        mTaskView.setLayoutManager(new LinearLayoutManager(getActivity()));

        testTaskRecycle();

        return v;
    }

    private class TaskHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mTaskTitle;
        private TextView mTaskContent;
        private ImageView mTaskImage;
        private TextView mTaskTime;
        private TextView mTaskGift;
        private Task mTask;

        public TaskHolder(LayoutInflater inflater,ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_task,parent,false));

            mTaskTitle = (TextView)itemView.findViewById(R.id.task_title);
            mTaskContent = (TextView)itemView.findViewById(R.id.task_content);
            mTaskTime = (TextView)itemView.findViewById(R.id.task_time);
            mTaskGift = (TextView)itemView.findViewById(R.id.task_gift);
            itemView.setOnClickListener(this);
        }

        public void bind(Task task){
            mTask = task;
            mTaskTitle.setText(mTask.getTaskTitle());
            mTaskContent.setText(mTask.getTaskContent());
            mTaskTime.setText(mTask.getTaskTime());
            mTaskGift.setText(mTask.getTaskGift()+"元");
        }

        @Override
        public void onClick(View view){
            startActivity(new Intent(getActivity(),TaskActivity.class));
        }
    }

    private class TaskAdapter extends RecyclerView.Adapter<TaskHolder>{

        private List<Task> mTasks;

        public TaskAdapter(List<Task> tasks){
            mTasks = tasks;
        }

        @Override
        public TaskHolder onCreateViewHolder(ViewGroup parent,int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new TaskHolder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(TaskHolder holder,int position){
            Task task = mTasks.get(position);
            holder.bind(task);
        }

        @Override
        public int getItemCount() {
            return mTasks.size();
        }
    }

    public void testTaskRecycle(){
        List<Task> tasks = new ArrayList<Task>();
        Task task = new Task();
        task.setTaskTitle("任务标题");
        task.setTaskContent("详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情");
        task.setTaskTime("2018/11/30");
        task.setTaskGift(5);
        for(int i=0;i<100;i++){
            tasks.add(task);
        }
        mAdapter = new TaskAdapter(tasks);
        mTaskView.setAdapter(mAdapter);
        mTaskView.addItemDecoration(new RecyclerItemDecoration(24));
    }
}
