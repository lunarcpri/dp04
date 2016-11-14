package domain;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;


@Entity
@Access(AccessType.PROPERTY)
public class CreditCard extends DomainEntity {

    private String holder_name;
    private String brand_name;
    private int Number;
    private int  expired_month;
    private int expired_year;
    private String ccv;
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
    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    @Range(min=0, max=12)
    public int getExpired_month() {
        return expired_month;
    }

    public void setExpired_month(int expired_month) {
        this.expired_month = expired_month;
    }


    public int getExpired_year() {
        return expired_year;
    }

    public void setExpired_year(int expired_year) {
        this.expired_year = expired_year;
    }

    @Range(min=100, max=999)
    public String getCcv() {
        return ccv;
    }

    public void setCcv(String ccv) {
        this.ccv = ccv;
    }

    @OneToOne
    public Sponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }
}
