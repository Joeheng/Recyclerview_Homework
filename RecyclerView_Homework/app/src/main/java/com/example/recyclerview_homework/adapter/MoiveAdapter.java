package com.example.recyclerview_homework.adapter;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recyclerview_homework.Detail_Movie_Activity;
import com.example.recyclerview_homework.Edit_ItemActivity;
import com.example.recyclerview_homework.MainActivity;
import com.example.recyclerview_homework.R;
import com.example.recyclerview_homework.model.Movie;

import java.util.List;

public class MoiveAdapter extends RecyclerView.Adapter<MoiveAdapter.ViewHolder> {
    private AppCompatActivity context;
    private List<Movie> movieList;
    final static int REQUEST_CODE = 1;
    private int itemPosition;
    private ItemPosition callback;

    public MoiveAdapter(List<Movie> movieList, AppCompatActivity context) {
        this.context = context;
        this.movieList = movieList;
        this.callback = (ItemPosition) context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MoiveAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.custom_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {
        final Movie movie = movieList.get(position);

        viewHolder.name.setText(movie.getMovieTitle());
        viewHolder.date.setText(movie.getDate());
        viewHolder.imgMovie.setImageResource(movie.getImageMovie());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Detail_Movie_Activity.class);
                intent.putExtra("IMAGE", movie.getImageMovie());
                intent.putExtra("TITLE", movie.getMovieTitle());
                intent.putExtra("DATE", movie.getDate());
                intent.putExtra("SUMMARY", movie.getSummary());
                intent.putExtra("DURATION", movie.getDuration());
                Bundle b = new Bundle();
                b.putParcelable("movie", movie);
                intent.putExtras(b);
                context.startActivityForResult(intent, REQUEST_CODE);
            }
        });
        viewHolder.imgMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnPopupClicked(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, date;
        ImageView imgMore, imgMovie;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_Name);
            date = itemView.findViewById(R.id.tv_Date);
            imgMovie = itemView.findViewById(R.id.imageMovie);
            imgMore = itemView.findViewById(R.id.imageMore);
        }
    }

    //create menu function
    public void OnPopupClicked(final View v, final int position) {
        PopupMenu popupMenu = new PopupMenu(context, v);
        popupMenu.inflate(R.menu.more_menu);
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.edit:
                        callback.itemPosition(position);
                        return true;
                    case R.id.remove:
                        removeRecyclerviewItem(position);
                        Toast.makeText(context, "remove", Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        return true;
                }
            }
        });
    }
    public void removeRecyclerviewItem(int position) {
        this.movieList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, movieList.size());
    }
    public interface ItemPosition{
        void itemPosition(int position);
    }

}
