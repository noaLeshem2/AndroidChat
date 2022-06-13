/*package com.example.androidchat.adapters;



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

import com.example.androidchat.Post;
import com.example.androidchat.R;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.PostViewHolder>{

    class PostViewHolder extends RecyclerView.ViewHolder{
        private final TextView disName;
        private final TextView lastMsg;
        private final TextView lastTime;
        private final de.hdodenhof.circleimageview.CircleImageView imageView;

        private PostViewHolder(View item){
            super(item);
            disName = item.findViewById(R.id.);
            lastMsg = item.findViewById(R.id);
            lastTime = item.findViewById(R.id);
            imageView = item.findViewById(R.id);
        }
    }
    private final LayoutInflater myLayout;
    private List<Post> posts;

    public UserAdapter(Context context){
        myLayout = LayoutInflater.from(context);
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = myLayout.inflate(R.layout.user_container,parent,false);
        return new PostViewHolder(itemView);
    }

    @Override
    public  void onBindViewHolder(PostViewHolder holder, int position){
        if(posts != null){
            final Post current = posts.get(position);
            holder.lastMsg.setText(current.getContent());
            holder.lastTime.setText(current.getTime());
            holder.disName.setText(current.getDisplayName());
            //convert the image to Bitmap

            Image image = current.getImage();
            ByteBuffer buffer = image.getPlanes()[0].getBuffer();
            byte[] bytes = new byte[buffer.capacity()];
            buffer.get(bytes);
            Bitmap bitmapImage = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, null);
            holder.imageView.setImageBitmap(bitmapImage);

        }
    }

    public void setPosts(List<Post> posts){
        this.posts =posts;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount(){
        if (posts != null)
            return posts.size();
        else return 0;
    }

    public List<Post> getPosts() {return posts;}
}*/
