package com.widya_angraini.widyauas.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.widya_angraini.widyauas.EditActivity;
import com.widya_angraini.widyauas.MainActivity;
import com.widya_angraini.widyauas.R;
import com.widya_angraini.widyauas.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder>{
    List<User> mUserList;


    public UserAdapter(List<User> userList){
        mUserList=userList;
    }

    public UserAdapter(MainActivity mainActivity, List<User> userList) {
    }

    @NonNull
    @Override
    public UserAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(mView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.MyViewHolder holder, int position) {
        holder.mTextViewId.setText("Id = "+mUserList.get(position).getId());
        holder.mTextViewEmail.setText("Email = "+mUserList.get(position).getEmail());
        holder.mTextViewFirstName.setText("First Name = "+mUserList.get(position).getFirst_name());
        holder.mTextViewLastName.setText("Last Name = "+mUserList.get(position).getLast_name());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), EditActivity.class);
                mIntent.putExtra("Id",mUserList.get(position).getId());
                mIntent.putExtra("Email",mUserList.get(position).getEmail());
                mIntent.putExtra("First Name",mUserList.get(position).getFirst_name());
                mIntent.putExtra("Last Name",mUserList.get(position).getLast_name());
                mIntent.putExtra("Avatar",mUserList.get(position).getAvatar());
                view.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewId, mTextViewName, mTextViewEmail, mTextViewFirstName, mTextViewLastName;
        public ImageView mImageViewAvatar;

        public MyViewHolder(View itemView){
            super(itemView);
            mTextViewId = (TextView)itemView.findViewById(R.id.tvId);
            mTextViewEmail = (TextView)itemView.findViewById(R.id.tvEmail);
            mTextViewFirstName = (TextView)itemView.findViewById(R.id.tvFirstName);
            mTextViewLastName = (TextView)itemView.findViewById(R.id.tvLastName);
            mImageViewAvatar = (ImageView)itemView.findViewById(R.id.ivAvatar);
        }
    }
}
