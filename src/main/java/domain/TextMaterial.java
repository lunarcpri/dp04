package domain;


import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

@Entity
@Access(AccessType.PROPERTY)
public class TextMaterial extends LearningMaterial {

    private String body;

    @SafeHtml
    @NotBlank
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
