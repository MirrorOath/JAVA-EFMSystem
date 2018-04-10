package dao;

public class First {
    private Integer bookId;
    private String bookName;
    private String press;
    private String author;
    private String imageSrc;
    public Integer getBookId() {
        return bookId;
    }
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
    public String getBookName() {
        return bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public String getPress() {
        return press;
    }
    public void setPress(String press) {
        this.press = press;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getImageSrc() {
        return imageSrc;
    }
    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }
    @Override
    public String toString() {
        return "BookInfo [bookId=" + bookId + ", bookName=" + bookName + ", press=" + press + ", author=" + author
                + ", imageSrc=" + imageSrc + "]";
    }
}
