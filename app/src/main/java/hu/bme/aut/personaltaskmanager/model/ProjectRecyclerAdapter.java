package hu.bme.aut.personaltaskmanager.model;


import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hu.bme.aut.personaltaskmanager.R;
import hu.bme.aut.personaltaskmanager.ui.handling_tasks.OnProjectSelectedListener;

public class ProjectRecyclerAdapter extends RecyclerView.Adapter<ProjectRecyclerAdapter.ProjectViewHolder>{

    public static final int EDIT_PROJECT_REQUEST_CODE = 101;

    private List<Project> projects;
    private OnProjectSelectedListener listener;

    public ProjectRecyclerAdapter(OnProjectSelectedListener listener){
        projects = DataManager.getInstance().getProjects();
        this.listener = listener;
    }

    @Override
    public ProjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_item, parent, false);
        ProjectViewHolder viewHolder = new ProjectViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ProjectViewHolder holder, int position) {
        Project project = projects.get(position);
        holder.titleTextView.setText(project.getTitle());
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    //TODO: other list for projects in this class or just send notification
    //public void addProject(Project p) {
    //    projects.add(p);
    //    notifyItemInserted(projects.size() - 1);
    //}

    public void projectAdded(){ notifyDataSetChanged(); }

    //public void update(List<Project> shoppingItems) {
    //    projects.clear();
    //    projects.addAll(shoppingItems);
    //    notifyDataSetChanged();
    //}


    public class ProjectViewHolder extends RecyclerView.ViewHolder{

        ImageView iconImageView;
        TextView titleTextView;
        ImageButton editButton;
        ImageButton removeButton;

        public ProjectViewHolder(final View itemView) {
            super(itemView);

            iconImageView = (ImageView) itemView.findViewById(R.id.ProjectFolderIconImageView);
            titleTextView = (TextView) itemView.findViewById(R.id.ProjectTitle);
            editButton = (ImageButton) itemView.findViewById(R.id.ProjectEditButton);
            //TODO: implementation of the edit button
            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //NewProjectDialogFragment dialog = new NewProjectDialogFragment();
                    //dialog.setTargetFragment(frag, EDIT_PROJECT_REQUEST_CODE);
                    //dialog.show(getFragmentManager(), NewProjectDialogFragment.TAG);
                }
            });
            removeButton = (ImageButton) itemView.findViewById(R.id.ProjectRemoveButton);
            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder adb = new AlertDialog.Builder(itemView.getContext());
                    adb.setMessage(R.string.delete_project_alert_msg);
                    adb.setTitle("Delete " + projects.get(getAdapterPosition()).getTitle() + " Project");
                    adb.setIcon(android.R.drawable.ic_dialog_alert);
                    adb.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Project p = projects.remove(getAdapterPosition());
                            DataManager.getInstance().removeProject(p);
                            notifyItemRemoved(getAdapterPosition());
                        }
                    });
                    adb.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            return;
                        }
                    });
                    adb.show();
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onProjectSelected(getAdapterPosition());
                    }
                }
            });
        }
    }
}
