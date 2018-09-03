package spms.vo;

import java.util.Date;

/**
 * IDE : IntelliJ IDEA
 * Created by minho on 2018. 9. 3..
 */
public class Project {
    protected int no;
    protected String name;
    protected String content;
    protected Date startDate;
    protected Date endDate;
    protected int state;
    protected Date createdDate;
    protected String tags;

    public int getNo() {
        return no;
    }

    public Project setNo(int no) {
        this.no = no;
        return this;
    }

    public String getName() {
        return name;
    }

    public Project setName(String name) {
        this.name = name;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Project setContent(String content) {
        this.content = content;
        return this;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Project setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Project setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public int getState() {
        return state;
    }

    public Project setState(int state) {
        this.state = state;
        return this;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Project setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public String getTags() {
        return tags;
    }

    public Project setTags(String tags) {
        this.tags = tags;
        return this;
    }
}
