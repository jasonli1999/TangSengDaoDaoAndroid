package com.idss.cashloans.api.utils;

/**
 * Create time 2023/3/8 16:56
 */
public class ContactsInfo {
    String contactId;
    String displayName;
    String photoUri;
    String photoPath;
    String firstName;
    String lastName;
    String company;
    String department;
    String job;
    String jobDescription;
    String emailAddress;
    String emailAddressDisplayName;
    String note;
    String nickName;
    String webUrl;
    String relationName;
    String protocol;
    String customProtocol;
    String identity;
    String namespace;
    String groupId;
    ContactsNumber contactsNumbers;
    String number;
    int numberType;
    String label;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getNumberType() {
        return numberType;
    }

    public void setNumberType(int numberType) {
        this.numberType = numberType;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getDisplayName() {
        return displayName == null ? "" : displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPhotoUri() {
        return photoUri == null ? "" : photoUri;
    }

    public void setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
    }

    public String getPhotoPath() {
        return photoPath == null ? "" : photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getFirstName() {
        return firstName == null ? "" : firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName == null ? "" : lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompany() {
        return company == null ? "" : company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDepartment() {
        return department == null ? "" : department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJob() {
        return job == null ? "" : job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getJobDescription() {
        return jobDescription == null ? "" : jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getEmailAddress() {
        return emailAddress == null ? "" : emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailAddressDisplayName() {
        return emailAddressDisplayName == null ? "" : emailAddressDisplayName;
    }

    public void setEmailAddressDisplayName(String emailAddressDisplayName) {
        this.emailAddressDisplayName = emailAddressDisplayName;
    }

    public String getNote() {
        return note == null ? "" : note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNickName() {
        return nickName == null ? "" : nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getWebUrl() {
        return webUrl == null ? "" : webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getRelationName() {
        return relationName == null ? "" : relationName;
    }

    public void setRelationName(String relationName) {
        this.relationName = relationName;
    }

    public String getProtocol() {
        return protocol == null ? "" : protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getCustomProtocol() {
        return customProtocol == null ? "" : customProtocol;
    }

    public void setCustomProtocol(String customProtocol) {
        this.customProtocol = customProtocol;
    }

    public String getIdentity() {
        return identity == null ? "" : identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getNamespace() {
        return namespace == null ? "" : namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getGroupId() {
        return groupId == null ? "" : groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }


    @Override
    public String toString() {
        return "ContactsInfo{" +
                "contactId=" + contactId +
                ", displayName='" + displayName + '\'' +
                ", photoUri='" + photoUri + '\'' +
                ", photoPath='" + photoPath + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", company='" + company + '\'' +
                ", department='" + department + '\'' +
                ", job='" + job + '\'' +
                ", jobDescription='" + jobDescription + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", emailAddressDisplayName='" + emailAddressDisplayName + '\'' +
                ", note='" + note + '\'' +
                ", nickName='" + nickName + '\'' +
                ", webUrl='" + webUrl + '\'' +
                ", relationName='" + relationName + '\'' +
                ", protocol='" + protocol + '\'' +
                ", customProtocol='" + customProtocol + '\'' +
                ", identity='" + identity + '\'' +
                ", namespace='" + namespace + '\'' +
                ", groupId='" + groupId + '\'' +
                ", contactsNumbers=" + contactsNumbers +
                '}';
    }

    public static class ContactsNumber {
        int numberType;
        String label;
        String number;

        public int getNumberType() {
            return numberType;
        }

        public void setNumberType(int numberType) {
            this.numberType = numberType;
        }

        public String getLabel() {
            return label == null ? "" : label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getNumber() {
            return number == null ? "" : number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        @Override
        public String toString() {
            return "ContactsNumber{" +
                    "numberType=" + numberType +
                    ", label='" + label + '\'' +
                    ", number='" + number + '\'' +
                    '}';
        }

    }
}