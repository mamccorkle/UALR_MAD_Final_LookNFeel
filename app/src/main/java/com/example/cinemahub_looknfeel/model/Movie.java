package com.example.cinemahub_looknfeel.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Movie implements Parcelable
{
    private String       title;
    private String       date;
    private Rating       rating;
    private double       score;
    private int          runtime;
    private String       synopsis;
    private String       director;
    private List<String> cast;
    private Genre        genre;
    private List<String> showTimes;
    private int          imageIndex;
    private String       youtubeLink;

    // Parcelables Required describeContents:
    @Override
    public int describeContents() {
        return 0;
    }

    // Parcelables Required writeToParcel:
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.title);
        parcel.writeString(this.date);
        parcel.writeString(this.rating.name());
        parcel.writeDouble(this.score);
        parcel.writeInt(this.runtime);
        parcel.writeString(this.synopsis);
        parcel.writeString(this.director);
        parcel.writeStringList(this.cast);
        parcel.writeString(this.genre.name());
        parcel.writeStringList(this.showTimes);
        parcel.writeInt(this.imageIndex);
        parcel.writeString(this.youtubeLink);
    }

    // Parcelables Required Creator:
    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }
        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    // Parcelables Required constructor:
    protected Movie(Parcel in) {
        this.title       = in.readString();
        this.date        = in.readString();
        this.rating      = Rating.valueOf(in.readString());
        this.score       = in.readDouble();
        this.runtime     = in.readInt();
        this.synopsis    = in.readString();
        this.director    = in.readString();
        this.cast        = in.createStringArrayList();
        this.genre       = Genre.valueOf(in.readString());
        this.showTimes   = in.createStringArrayList();;
        this.imageIndex  = in.readInt();
        this.youtubeLink = in.readString();
    }

    // Constructor:
    public Movie() {}
    public Movie(String title,
                 String date,
                 Rating rating,
                 double score,
                 int runtime,
                 String synopsis,
                 String director,
                 List<String> cast,
                 Genre genre,
                 List<String> showTimes,
                 int imageIndex,
                 String youtubeLink)
    {
        this.title       = title;
        this.date        = date;
        this.rating      = rating;
        this.score       = score;
        this.runtime     = runtime;
        this.synopsis    = synopsis;
        this.director    = director;
        this.cast        = cast;
        this.genre       = genre;
        this.showTimes   = showTimes;
        this.imageIndex  = imageIndex;
        this.youtubeLink = youtubeLink;
    }

    // Getters:
    public String       getTitle()       { return title;       }
    public String       getDate()        { return date;        }
    public Rating       getRating()      { return rating;      }
    public double       getScore()       { return score;       }
    public int          getRuntime()     { return runtime;     }
    public String       getSynopsis()    { return synopsis;    }
    public String       getDirector()    { return director;    }
    public List<String> getCast()        { return cast;        }
    public Genre        getGenre()       { return genre;       }
    public List<String> getShowTimes()   { return showTimes;   }
    public int          getImageIndex()  { return imageIndex;  }
    public String       getYoutubeLink() { return youtubeLink; }

    // Setters:
    public void setTitle(String title)               { this.title = title;             }
    public void setDate(String date)                 { this.date = date;               }
    public void setRating(Rating rating)             { this.rating = rating;           }
    public void setScore(double score)               { this.score = score;             }
    public void setRuntime(int runtime)              { this.runtime = runtime;         }
    public void setSynopsis(String synopsis)         { this.synopsis = synopsis;       }
    public void setDirector(String director)         { this.director = director;       }
    public void setCast(List<String> cast)           { this.cast = cast;               }
    public void setGenre(Genre genre)                { this.genre = genre;             }
    public void setShowTimes(List<String> showTimes) { this.showTimes = showTimes;     }
    public void setImageIndex(int imageIndex)        { this.imageIndex = imageIndex;   }
    public void setYoutubeLink(String youtubeLink)   { this.youtubeLink = youtubeLink; }

    // Other:
    public void addCast(String cast)       { this.cast.add(cast);      }
    public void addShowTime(String time)   { this.showTimes.add(time); }
}