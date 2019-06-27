package com.example.recyclerview_homework;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.widget.SearchView;

import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.recyclerview_homework.adapter.MoiveAdapter;
import com.example.recyclerview_homework.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MoiveAdapter.ItemPosition {
    private MoiveAdapter moiveAdapter;
    private RecyclerView rv;
    private int position;
    private List<Movie> movies = new ArrayList<>();
    final static int CODE_REQUEST = 1;
    final static int EDIT_REQUEST_CODE = 100;
    FloatingActionButton btnAdd;
    String[] title = {
            "Alita: Battle Angel",
            "Aquaman",
            "Shazam!",
            "The Grinch",
            "Aladdin",
            "Dumbo",
            "Captian Marvel",
            "Venom",
            "Avengers: Endgame",
            "Ant-Man"
    };
    String[] dates = {
            "21/02/2019",
            "07/12/2018",
            "03/04/2019",
            "08/11/2018",
            "24/05/2019",
            "29/03/2019",
            "08/03/2019",
            "5/10/2018",
            "26/04/2019",
            "17/07/2017"
    };
    int[] images = {
            R.drawable.m1,
            R.drawable.m2,
            R.drawable.m3,
            R.drawable.m4,
            R.drawable.m6,
            R.drawable.m8,
            R.drawable.m10,
            R.drawable.m11,
            R.drawable.m12,
            R.drawable.m13
    };
    String[] summaries = {
            "Set several centuries in the future, the abandoned Alita is found in the scrapyard of Iron City by Ido, a compassionate cyber-doctor who takes the unconscious cyborg Alita to his clinic. When Alita awakens, she has no memory of who she is, nor does she have any recognition of the world she finds herself in. As Alita learns to navigate her new life and the treacherous streets of Iron City, Ido tries to shield her from her mysterious past.",
            "Once home to the most advanced civilization on Earth, the city of Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Aquaman, Orm's half-human, half-Atlantean brother and true heir to the throne. With help from royal counselor Vulko, Aquaman must retrieve the legendary Trident of Atlan and embrace his destiny as protector of the deep.",
            "We all have a superhero inside of us it just takes a bit of magic to bring it out. In 14-year-old Billy Batson's case, all he needs to do is shout out one word to transform into the adult superhero Shazam. Still a kid at heart, Shazam revels in the new version of himself by doing what any other teen would do have fun while testing out his newfound powers. But he'll need to master them quickly before the evil Dr. Thaddeus Sivana can get his hands on Shazam's magical abilities.",
            "A grumpy Grinch plots to ruin Christmas for the village of Whoville.",
            "Aladdin is a lovable street urchin who meets Princess Jasmine, the beautiful daughter of the sultan of Agrabah. While visiting her exotic palace, Aladdin stumbles upon a magic oil lamp that unleashes a powerful, wisecracking, larger-than-life genie. As Aladdin and the genie start to become friends, they must soon embark on a dangerous mission to stop the evil sorcerer Jafar from overthrowing young Jasmine's kingdom.",
            "Struggling circus owner Max Medici enlists a former star and his two children to care for Dumbo, a baby elephant born with oversized ears. When the family discovers that the animal can fly, it soon becomes the main attraction — bringing in huge audiences and revitalizing the run-down circus. The elephant's magical ability also draws the attention of V.A. Vandevere, an entrepreneur who wants to showcase Dumbo in his latest, larger-than-life entertainment venture.",
            "Captain Marvel is an extraterrestrial Kree warrior who finds herself caught in the middle of an intergalactic battle between her people and the Skrulls. Living on Earth in 1995, she keeps having recurring memories of another life as U.S. Air Force pilot Carol Danvers. With help from Nick Fury, Captain Marvel tries to uncover the secrets of her past while harnessing her special superpowers to end the war with the evil Skrulls.",
            "Journalist Eddie Brock is trying to take down Carlton Drake, the notorious and brilliant founder of the Life Foundation. While investigating one of Drake's experiments, Eddie's body merges with the alien Venom — leaving him with superhuman strength and power. Twisted, dark and fueled by rage, Venom tries to control the new and dangerous abilities that Eddie finds so intoxicating.",
            "Adrift in space with no food or water, Tony Stark sends a message to Pepper Potts as his oxygen supply starts to dwindle. Meanwhile, the remaining Avengers — Thor, Black Widow, Captain America and Bruce Banner — must figure out a way to bring back their vanquished allies for an epic showdown with Thanos — the evil demigod who decimated the planet and the universe.",
            "Forced out of his own company by former protégé Darren Cross, Dr. Hank Pym (Michael Douglas) recruits the talents of Scott Lang (Paul Rudd), a master thief just released from prison. Lang becomes Ant-Man, trained by Pym and armed with a suit that allows him to shrink in size, possess superhuman strength and control an army of ants. The miniature hero must use his new skills to prevent Cross, also known as Yellowjacket, from perfecting the same technology and using it as a weapon for evil."
    };
    String[] duration = {
            "142 mins",
            "142 mins",
            "104 mins",
            "92 mins",
            "118 mins",
            "110 mins",
            "107 mins",
            "112 mins",
            "100 mins",
            "132 mins"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        getMovies();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, Add_ItemActivity.class), CODE_REQUEST);
            }
        });
        previousMovie.addAll(movies);
        }


    private void initUI() {
        rv = findViewById(R.id.recyclerView);
        btnAdd = findViewById(R.id.Add);
        rv.setLayoutManager(new LinearLayoutManager(this));
        moiveAdapter = new MoiveAdapter(movies, this);
        rv.setAdapter(moiveAdapter);
    }

    private void getMovies() {
        for (int i = 0; i < title.length; i++) {
            this.movies.add(new Movie(title[i], dates[i], duration[i], summaries[i], images[i]));
        }
        moiveAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (CODE_REQUEST == requestCode && resultCode == RESULT_OK) {
            Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
            Movie movie = data.getParcelableExtra("movie");
            this.movies.add(0, movie);
            moiveAdapter.notifyItemInserted(0);
            Scroll(0);
        }
        if (EDIT_REQUEST_CODE == requestCode && resultCode == RESULT_OK) {
            Toast.makeText(this, "edit successfully", Toast.LENGTH_SHORT).show();
            Movie movie = data.getParcelableExtra("film");
            this.movies.set(this.position, movie);
            moiveAdapter.notifyItemChanged(this.position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                doSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                return false;
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                movies.clear();
                movies.addAll(previousMovie);
                moiveAdapter.notifyDataSetChanged();
                return false;
            }
        });
        return true;
    }


    List<Movie> previousMovie = new ArrayList<>();

    private void doSearch(String query) {
        if (query.isEmpty()) {
            return;
        }
        if (movies.size() == 0)
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();

        movies.clear();
        for (Movie movie : previousMovie) {
            if (movie.getMovieTitle().equalsIgnoreCase(query)) {
                movies.add(movie);
            }
        }
        moiveAdapter.notifyDataSetChanged();
    }

    private void Scroll(int position) {
        rv.smoothScrollToPosition(position);
    }

    @Override
    public void itemPosition(int position) {
        this.position = position;
        Movie movie=this.movies.get(position);
        Intent intent = new Intent(this, Edit_ItemActivity.class);
        Bundle b = new Bundle();
        b.putParcelable("film", movie);
        intent.putExtras(b);
        startActivityForResult(intent,EDIT_REQUEST_CODE);
    }

}
