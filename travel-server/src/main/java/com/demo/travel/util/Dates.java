package com.demo.travel.util;

import com.demo.travel.entity.Comment;

import java.util.Comparator;

public class Dates {
    public static Comparator<Comment> DescComparator = (a, b) -> b.getCreateTime().compareTo(a.getCreateTime());
    public static Comparator<Comment> AscComparator = (a, b) -> a.getCreateTime().compareTo(b.getCreateTime());
}

