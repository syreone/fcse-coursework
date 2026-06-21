package labExercises.lab8.exercise1;
import java.util.ArrayList;
import java.util.List;

public class PatternTest {
    public static void main(String args[]) {
        List<Song> listSongs = new ArrayList<Song>();
        listSongs.add(new Song("first-title", "first-artist"));
        listSongs.add(new Song("second-title", "second-artist"));
        listSongs.add(new Song("third-title", "third-artist"));
        listSongs.add(new Song("fourth-title", "fourth-artist"));
        listSongs.add(new Song("fifth-title", "fifth-artist"));
        MP3Player player = new MP3Player(listSongs);


        System.out.println(player.toString());
        System.out.println("First test");


        player.pressPlay();
        player.printCurrentSong();
        player.pressPlay();
        player.printCurrentSong();

        player.pressPlay();
        player.printCurrentSong();
        player.pressStop();
        player.printCurrentSong();

        player.pressPlay();
        player.printCurrentSong();
        player.pressFWD();
        player.printCurrentSong();

        player.pressPlay();
        player.printCurrentSong();
        player.pressREW();
        player.printCurrentSong();


        System.out.println(player.toString());
        System.out.println("Second test");


        player.pressStop();
        player.printCurrentSong();
        player.pressStop();
        player.printCurrentSong();

        player.pressStop();
        player.printCurrentSong();
        player.pressPlay();
        player.printCurrentSong();

        player.pressStop();
        player.printCurrentSong();
        player.pressFWD();
        player.printCurrentSong();

        player.pressStop();
        player.printCurrentSong();
        player.pressREW();
        player.printCurrentSong();


        System.out.println(player.toString());
        System.out.println("Third test");


        player.pressFWD();
        player.printCurrentSong();
        player.pressFWD();
        player.printCurrentSong();

        player.pressFWD();
        player.printCurrentSong();
        player.pressPlay();
        player.printCurrentSong();

        player.pressFWD();
        player.printCurrentSong();
        player.pressStop();
        player.printCurrentSong();

        player.pressFWD();
        player.printCurrentSong();
        player.pressREW();
        player.printCurrentSong();


        System.out.println(player.toString());
    }
}

class Song {
    private String title;
    private String artist;

    public Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    @Override
    public String toString() {
        return String.format("Song{title=%s, artist=%s}", title, artist);
    }
}

class MP3Player {
    private List<Song> songList;
    private int currentSong;
    private boolean isPlaying;
    private boolean isStopped;

    public MP3Player(List<Song> songList) {
        this.songList = songList;
        this.currentSong = 0;
        this.isPlaying = false;
        this.isStopped = true;
    }

    public void pressPlay() {
        if (isPlaying) {
            System.out.println("Song is already playing");
        } else {
            isPlaying = true;
            isStopped = false;
            System.out.printf("Song %d is playing%n", currentSong);
        }
    }

    public void pressStop() {
        if (isPlaying) {
            isPlaying = false;
            System.out.printf("Song %d is paused%n", currentSong);
        } else {
            if (isStopped) {
                System.out.println("Songs are already stopped");
            } else {
                currentSong = 0;
                isStopped = true;
                System.out.println("Songs are stopped");
            }
        }
    }

    public void pressFWD() {
        System.out.println("Forward...");
        isPlaying = false;
        isStopped = false;
        currentSong = (currentSong + 1) % songList.size();
    }

    public void pressREW() {
        System.out.println("Reward...");
        isPlaying = false;
        isStopped = false;
        currentSong = (currentSong - 1 + songList.size()) % songList.size();
    }

    public void printCurrentSong() {
        System.out.println(songList.get(currentSong));
    }

    @Override
    public String toString() {
        return String.format(
                "MP3Player{currentSong = %d, songList = %s}",
                currentSong, songList
        );
    }
}

