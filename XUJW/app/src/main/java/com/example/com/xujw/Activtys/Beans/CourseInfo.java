package com.example.com.xujw.Activtys.Beans;

/**
 * Created by 蒲家旺 on 2017/2/10.
 */
public class CourseInfo {

    private String Course_name;//课程名称
    private String Course_nature;//课程性质

    private String Course_belong;//课程归属

    private String Course_credit;//学分
    private String Course_make_up_score;//补考成绩
    private String Course_score;//成绩
    private String Course_average_point;//绩点
    private String Course_college;//开课学院

    public String getCourse_name() {
        return Course_name;
    }

    public String getCourse_average_point() {
        return Course_average_point;
    }

    public String getCourse_belong() {
        return Course_belong;
    }

    public String getCourse_make_up_score() {
        return Course_make_up_score;
    }

    public String getCourse_score() {
        return Course_score;
    }

    public String getCourse_credit() {
        return Course_credit;
    }

    public String getCourse_nature() {
        return Course_nature;
    }

    public String getCourse_college() {
        return Course_college;
    }

    public void setCourse_belong(String course_belong) {
        Course_belong = course_belong;
    }

    public void setCourse_average_point(String course_average_point) {
        Course_average_point = course_average_point;
    }

    public void setCourse_credit(String course_credit) {
        Course_credit = course_credit;
    }

    public void setCourse_college(String course_college) {
        Course_college = course_college;
    }

    public void setCourse_make_up_score(String course_make_up_score) {
        Course_make_up_score = course_make_up_score;
    }

    public void setCourse_name(String course_name) {
        Course_name = course_name;
    }

    public void setCourse_nature(String course_nature) {
        Course_nature = course_nature;
    }

    public void setCourse_score(String course_score) {
        Course_score = course_score;
    }
}
