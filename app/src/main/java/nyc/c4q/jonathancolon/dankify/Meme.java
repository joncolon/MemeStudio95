package nyc.c4q.jonathancolon.dankify;

import java.io.Serializable;

/**
 * Created by jonathancolon on 1/8/17.
 */

public class Meme implements Serializable {
    Long _id;
    String memeImage;
    String dateCreated;
    int memeType;

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getMemeImage() {
        return memeImage;
    }

    public void setMemeImage(String memeImage) {
        this.memeImage = memeImage;
    }

    public int getMemeType() {
        return memeType;
    }

    public void setMemeType(int memeType) {
        this.memeType = memeType;
    }

}
