package hu.bme.aut.personaltaskmanager.model;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import hu.bme.aut.personaltaskmanager.R;

public class TaskRecyclerAdapter extends RecyclerView.Adapter<TaskRecyclerAdapter.TaskViewHolder> {

    private List<Task> tasks;

    public TaskRecyclerAdapter(int projectPosition){
        tasks = DataManager.getInstance().getTasks(projectPosition);
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        TaskViewHolder viewHolder = new TaskViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.titleTextView.setText(task.getTitle());
        holder.dateTextView.setText(getFormattedDate(task.getDate(), "yyyy.MM.dd HH:mm"));
        holder.isDoneCheckbox.setChecked(task.isDone());
    }

    private String getFormattedDate(long date, String dateFormat)
    {
        DateFormat formatter = new SimpleDateFormat(dateFormat);

        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(date);
        return formatter.format(c.getTime());
    }

    //@Override
    //public void onBindViewHolder(final TaskViewHolder holder, int position) {
    //      Task task = items.get(position);
          //holder.nameTextView.setText(item.name);
          //holder.descriptionTextView.setText(item.description);
          //holder.categoryTextView.setText(item.category.name());
          //holder.priceTextView.setText(item.estimatedPrice + " Ft");
          //holder.iconImageView.setImageResource(getImageResource(item.category));
          //holder.isBoughtCheckBox.setChecked(item.isBought);
    //
          //holder.isBoughtCheckBox.setOnClickListener(new View.OnClickListener() {
          //   @Override
          //   public void onClick(View view) {
          //      boolean checked = holder.isBoughtCheckBox.isChecked();
          //      Log.d(TAG, "onClick: checked = " + checked + " in position "
          //              + holder.getAdapterPosition());
          //      ShoppingItem item = items.get(holder.getAdapterPosition());
          //      item.isBought = checked;
          //      item.save();
          //   }
          //});
    //}

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public void newTaskAdded(){ notifyDataSetChanged(); }

    public class TaskViewHolder extends RecyclerView.ViewHolder{

        ImageView iconImageView;
        CheckBox isDoneCheckbox;
        TextView titleTextView;
        TextView dateTextView;
        ImageButton editButton;
        ImageButton removeButton;

        public TaskViewHolder(View itemView) {
            super(itemView);

            //iconImageView = (ImageView) itemView.findViewById(R.id.TaskFolderIconImageView);
            isDoneCheckbox = (CheckBox) itemView.findViewById(R.id.TaskDoneCheckBox);
            // TODO: implement checkbox click
            isDoneCheckbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean checked = isDoneCheckbox.isChecked();
                    //      Log.d(TAG, "onClick: checked = " + checked + " in position "
                    //              + holder.getAdapterPosition());
                    tasks.get(getAdapterPosition()).setDone(checked);
                    // TODO: notify recycle view, remove checked task
                }
            });

            titleTextView = (TextView) itemView.findViewById(R.id.TaskTitle);
            dateTextView = (TextView) itemView.findViewById(R.id.TaskDate);

            editButton = (ImageButton) itemView.findViewById(R.id.TaskEditButton);
            //TODO: implementation of the edit button
            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            removeButton = (ImageButton) itemView.findViewById(R.id.TaskRemoveButton);
            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Task t = tasks.remove(getAdapterPosition());
                    //t.delete();
                    notifyItemRemoved(getAdapterPosition());
                }
            });
        }
    }
}
