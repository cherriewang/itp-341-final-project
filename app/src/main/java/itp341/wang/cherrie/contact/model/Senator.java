package itp341.wang.cherrie.contact.model;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Cherrie on 12/10/16.
 */

public class Senator implements Parcelable {

    private String name;
    private String party;
    private String district;
    private String phone;
    private String office;
    private String link;
    private String state;

    public Senator(){

    }

    protected Senator(Parcel in) {
        name = in.readString();
        party = in.readString();
        district = in.readString();
        phone = in.readString();
        office = in.readString();
        link = in.readString();
        state = in.readString();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(party);
        parcel.writeString(district);
        parcel.writeString(phone);
        parcel.writeString(office);
        parcel.writeString(link);
        parcel.writeString(state);
    }

    public static final Creator<Senator> CREATOR = new Creator<Senator>() {
        @Override
        public Senator createFromParcel(Parcel in) {
            return new Senator(in);
        }

        @Override
        public Senator[] newArray(int size) {
            return new Senator[size];
        }
    };

    @Override
    public String toString(){
        String s = "Senator name: "+ this.name +  "\nSenator party: " + this.party
                + "\nRepresentative district: " + this.district +  "\nSenator state: " + this.state
                + "\nSenator phone number: " + this.phone + "\nSenator office address: "
                + this.office + "\nSenator website link: " + this.link;
        return s;

    }

}
