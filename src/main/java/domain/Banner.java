package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Access(AccessType.PROPERTY)
public class Banner extends DomainEntity {

    private int displayed_time;
    private Campaign campaign;

    public Banner()
    {
        super();
    }


    public int getDisplayed_time() {
        return displayed_time;
    }

    public void setDisplayed_time(int displayed_time) {
        this.displayed_time = displayed_time;
    }

    @ManyToOne
    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }
}
