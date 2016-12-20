

package domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import security.UserAccount;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.List;

@Entity
@Access(AccessType.PROPERTY)
public abstract class Actor extends DomainEntity {

	// Constructors -----------------------------------------------------------

	public Actor() {
		super();
	}

	// Attributes -------------------------------------------------------------

	private String name;
	private String email;
	private String surnames;
	private String phone;
	private String address;
	private Collection<Message> SendedMessages;
	private Collection<Message> ReceivedMessages;
	private List<SocialIdentity> socialIdentities;
    private UserAccount userAccount;
    private Collection<Star> starses;
	private Collection<Folder> folders;
	private Collection<MasterClass> attendingMasterClasses;


	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Email
	@NotBlank
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@NotBlank
	public String getSurnames() {
		return surnames;
	}

	public void setSurnames(String surnames) {
		this.surnames = surnames;
	}

	@Pattern(regexp = "^((\\+[0-9]{1,3})?\\s*(\\([0-9]{3}\\))?\\s*([a-zA-Z0-9\\- ]{4,}))?$")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	@Valid
    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
	public Collection<Message> getSendedMessages() {
		return SendedMessages;
	}

	public void setSendedMessages(Collection<Message> sendedMessages) {
		SendedMessages = sendedMessages;
	}

	@Valid
    @ManyToMany(mappedBy = "recipients", cascade = CascadeType.ALL)
	public Collection<Message> getReceivedMessages() {
		return ReceivedMessages;
	}

	public void setReceivedMessages(Collection<Message> receivedMessages) {
		ReceivedMessages = receivedMessages;
	}

	@Valid
	@OneToMany(mappedBy = "actor", cascade = CascadeType.ALL)
	public List<SocialIdentity> getSocialIdentities() {
		return socialIdentities;
	}

	public void setSocialIdentities(List<SocialIdentity> socialIdentities) {
		this.socialIdentities = socialIdentities;
	}

	@OneToOne(cascade = CascadeType.ALL, optional = true)
	public UserAccount getUserAccount()
    {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount)
    {
        this.userAccount = userAccount;
    }


    @Valid
    @OneToMany(mappedBy = "actor")
    public Collection<Star> getStarses() {
        return starses;
    }

    public void setStarses(Collection<Star> starses) {
        this.starses = starses;
    }

    @OneToMany(mappedBy = "actor",cascade = CascadeType.ALL)
    public Collection<Folder> getFolders() {
        return folders;
    }

    public void setFolders(Collection<Folder> folders) {
        this.folders = folders;
    }

	@ManyToMany
	public Collection<MasterClass> getAttendingMasterClasses() {
		return attendingMasterClasses;
	}

	public void setAttendingMasterClasses(Collection<MasterClass> masterClasses) {
		this.attendingMasterClasses = masterClasses;
	}
}