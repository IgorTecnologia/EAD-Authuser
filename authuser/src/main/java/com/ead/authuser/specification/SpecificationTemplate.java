package com.ead.authuser.specification;

import com.ead.authuser.entity.*;
import net.kaczmarzyk.spring.data.jpa.domain.*;
import net.kaczmarzyk.spring.data.jpa.web.annotation.*;
import org.springframework.data.jpa.domain.*;

public class SpecificationTemplate {
    @And({
        @Spec(path = "type", spec = Equal.class),
        @Spec(path = "status", spec = Equal.class),
        @Spec(path = "email", spec = Like.class)
        })
    public interface UserSpec extends Specification<User>{

    }
}
