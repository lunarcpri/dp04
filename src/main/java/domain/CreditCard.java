package domain;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;


@Entity
@Access(AccessType.PROPERTY)
public class CreditCard extends DomainEntity {

    private String holder_name;
    private String brand_name;
    private String number;
    private int  expired_month;
    private int expired_year;
    private int cvv;
    private Sponsor sponsor;

    public CreditCard()
    {
        super();
    }

    @NotBlank
    public String getHolder_name() {
        return holder_name;
    }

    public void setHolder_name(String holder_name) {
        this.holder_name = holder_name;
    }

    @NotBlank
    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    @CreditCardNumber
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Range(min=0, max=12)
    @NotNull
    public int getExpired_month() {
        return expired_month;
    }

    public void setExpired_month(int expired_month) {
        this.expired_month = expired_month;
    }

    @NotNull
    public int getExpired_year() {
        return expired_year;
    }

    public void setExpired_year(int expired_year) {
        this.expired_year = expired_year;
    }

    @Range(min=100, max=999)
    @NotNull
    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    @OneToOne
    public Sponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }


}
