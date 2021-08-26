package com.devjava.hourday.common.advice.exception.comment;

import com.devjava.hourday.common.advice.ExceptionCode;
import com.devjava.hourday.common.advice.exception.EntityNotFoundException;

public class CommentNotFoundException  extends EntityNotFoundException {

    public CommentNotFoundException() {
        super(ExceptionCode.COMMENT_NOT_FOUND);
    }

}
