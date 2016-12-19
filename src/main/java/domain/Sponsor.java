package domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)
public class Sponsor extends Actor {

    private String company;
    private CreditCard creditCard;
    private Collection<Bill> bills;
    private Collection<Campaign> campaigns;

    public Sponsor()
    {
        super();
    }

    @NotBlank
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }


    @Valid
    @OneToOne
    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    @Valid
    @OneToMany (mappedBy = "sponsor")
    public Collection<Bill> getBills() {
        return bills;
    }

    public void setBills(Collection<Bill> bills) {
        this.bills = bills;
    }


    @Valid
    @OneToMany(mappedBy = "sponsor",cascade = CascadeType.ALL)
    public Collection<Campaign> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(Collection<Campaign> campaigns) {
        this.campaigns = campaigns;
    }
}
