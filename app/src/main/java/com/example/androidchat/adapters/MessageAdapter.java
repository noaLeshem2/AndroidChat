package com.example.androidchat.adapters;



import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.example.androidchat.R;
import com.example.androidchat.entities.Message;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder>{

    class MessageViewHolder extends RecyclerView.ViewHolder{
        private int id_mess;
        private TextView content;
        private TextView time;
        private ImageView image;
        private TextView display_name;
        private TextView sent;

        private MessageViewHolder(View itemView){
            super(itemView);
           // image = itemView.findViewById(R.id.image);
            content = itemView.findViewById(R.id.contentMessage);
            //id_mess= itemView.findViewById(R.id.id_mess);
            time = itemView.findViewById(R.id.timeMessage);
            display_name= itemView.findViewById(R.id.displayFriendName);
            //sent = itemView.findViewById(R.id.sent);
        }
    }

    private final LayoutInflater myLayout;
    private List<Message> messages;

    public MessageAdapter(Context context){
        myLayout = LayoutInflater.from(context);
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = myLayout.inflate(R.layout.item_container_sent_message,parent,false);
        return new MessageViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position){
        if(messages != null){
            final Message current = messages.get(position);
            holder.content.setText(current.getContent());
            holder.time.setText(current.getTime());
            //holder.sent.setText(current.isSent());
        }
    }

    public void setMessages(List<Message> messages){
        this.messages = messages;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount(){
        if (messages != null)
            return messages.size();
        else return 0;
    }

    public List<Message> getMessages() {return messages;}
}
