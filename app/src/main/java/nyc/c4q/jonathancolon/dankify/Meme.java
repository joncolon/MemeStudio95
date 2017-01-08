package nyc.c4q.jonathancolon.dankify;

/**
 * Created by jonathancolon on 1/8/17.
 */

public class Meme {
    Long _id;
    public String topText;
    public String bottomText;
    String dateCreated;
    byte[] memeImage;

    int memeType;

    public String getTopText() {
        return topText;
    }

    public void setTopText(String topText) {
        this.topText = topText;
    }

    public String getBottomText() {
        return bottomText;
    }

    public void setBottomText(String bottomText) {
        this.bottomText = bottomText;
    }

    public byte[] getMemeImage() {
        return memeImage;
    }

    public void setMemeImage(byte[] memeImage) {
        this.memeImage = memeImage;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getMemeType() {
        return memeType;
    }

    public void setMemeType(int memeType) {
        this.memeType = memeType;
    }
}
