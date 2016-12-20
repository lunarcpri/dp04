package collections;


import domain.SocialIdentity;

import java.util.ArrayList;
import java.util.List;

public class SocialIdentityListWrapper {
    private List<SocialIdentity> socialIdentitiesList;

    public SocialIdentityListWrapper() {
        this.socialIdentitiesList = new ArrayList<SocialIdentity>();
    }

    public List<SocialIdentity> getFooList() {
        return socialIdentitiesList;
    }

    public void setFooList(List<SocialIdentity> socialIdentitiesList) {
        this.socialIdentitiesList = socialIdentitiesList;
    }

    public void add(SocialIdentity foo) {
        this.socialIdentitiesList.add(foo);
    }
}
