package com.jio.githublist.models;

import android.os.Parcel;
import android.os.Parcelable;

public class GitUsers implements Parcelable {

    public String login;
    public int id;
    public String node_id;
    public String avatar_url;
    public String gravatar_id;
    public String url;
    public String html_url;
    public String followers_url;
    public String following_url;
    public String gists_url;
    public String starred_url;
    public String subscriptions_url;
    public String organizations_url;
    public String repos_url;
    public String events_url;
    public String received_events_url;
    public String type;
    public boolean site_admin;
    public double score;

    public GitUsers(String login, int id, String node_id, String avatar_url, String gravatar_id, String url,
                    String html_url, String followers_url, String following_url, String gists_url,
                    String starred_url, String subscriptions_url, String organizations_url, String repos_url,
                    String events_url, String received_events_url, String type, boolean site_admin, double score) {
        this.login = login;
        this.id = id;
        this.node_id = node_id;
        this.avatar_url = avatar_url;
        this.gravatar_id = gravatar_id;
        this.url = url;
        this.html_url = html_url;
        this.followers_url = followers_url;
        this.following_url = following_url;
        this.gists_url = gists_url;
        this.starred_url = starred_url;
        this.subscriptions_url = subscriptions_url;
        this.organizations_url = organizations_url;
        this.repos_url = repos_url;
        this.events_url = events_url;
        this.received_events_url = received_events_url;
        this.type = type;
        this.site_admin = site_admin;
        this.score = score;
    }

    public GitUsers() {
    }

    protected GitUsers(Parcel in) {
        login = in.readString();
        id = in.readInt();
        node_id = in.readString();
        avatar_url = in.readString();
        gravatar_id = in.readString();
        url = in.readString();
        html_url = in.readString();
        followers_url = in.readString();
        following_url = in.readString();
        gists_url = in.readString();
        starred_url = in.readString();
        subscriptions_url = in.readString();
        organizations_url = in.readString();
        repos_url = in.readString();
        events_url = in.readString();
        received_events_url = in.readString();
        type = in.readString();
        site_admin = in.readByte() != 0;
        score = in.readDouble();
    }

    public static final Creator<GitUsers> CREATOR = new Creator<GitUsers>() {
        @Override
        public GitUsers createFromParcel(Parcel in) {
            return new GitUsers(in);
        }

        @Override
        public GitUsers[] newArray(int size) {
            return new GitUsers[size];
        }
    };

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNode_id() {
        return node_id;
    }

    public void setNode_id(String node_id) {
        this.node_id = node_id;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getGravatar_id() {
        return gravatar_id;
    }

    public void setGravatar_id(String gravatar_id) {
        this.gravatar_id = gravatar_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getFollowers_url() {
        return followers_url;
    }

    public void setFollowers_url(String followers_url) {
        this.followers_url = followers_url;
    }

    public String getFollowing_url() {
        return following_url;
    }

    public void setFollowing_url(String following_url) {
        this.following_url = following_url;
    }

    public String getGists_url() {
        return gists_url;
    }

    public void setGists_url(String gists_url) {
        this.gists_url = gists_url;
    }

    public String getStarred_url() {
        return starred_url;
    }

    public void setStarred_url(String starred_url) {
        this.starred_url = starred_url;
    }

    public String getSubscriptions_url() {
        return subscriptions_url;
    }

    public void setSubscriptions_url(String subscriptions_url) {
        this.subscriptions_url = subscriptions_url;
    }

    public String getOrganizations_url() {
        return organizations_url;
    }

    public void setOrganizations_url(String organizations_url) {
        this.organizations_url = organizations_url;
    }

    public String getRepos_url() {
        return repos_url;
    }

    public void setRepos_url(String repos_url) {
        this.repos_url = repos_url;
    }

    public String getEvents_url() {
        return events_url;
    }

    public void setEvents_url(String events_url) {
        this.events_url = events_url;
    }

    public String getReceived_events_url() {
        return received_events_url;
    }

    public void setReceived_events_url(String received_events_url) {
        this.received_events_url = received_events_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isSite_admin() {
        return site_admin;
    }

    public void setSite_admin(boolean site_admin) {
        this.site_admin = site_admin;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "GitUsers{" +
                "login='" + login + '\'' +
                ", id=" + id +
                ", node_id='" + node_id + '\'' +
                ", avatar_url='" + avatar_url + '\'' +
                ", gravatar_id='" + gravatar_id + '\'' +
                ", url='" + url + '\'' +
                ", html_url='" + html_url + '\'' +
                ", followers_url='" + followers_url + '\'' +
                ", following_url='" + following_url + '\'' +
                ", gists_url='" + gists_url + '\'' +
                ", starred_url='" + starred_url + '\'' +
                ", subscriptions_url='" + subscriptions_url + '\'' +
                ", organizations_url='" + organizations_url + '\'' +
                ", repos_url='" + repos_url + '\'' +
                ", events_url='" + events_url + '\'' +
                ", received_events_url='" + received_events_url + '\'' +
                ", type='" + type + '\'' +
                ", site_admin=" + site_admin +
                ", score=" + score +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(login);
        parcel.writeInt(id);
        parcel.writeString(node_id);
        parcel.writeString(avatar_url);
        parcel.writeString(gravatar_id);
        parcel.writeString(url);
        parcel.writeString(html_url);
        parcel.writeString(followers_url);
        parcel.writeString(following_url);
        parcel.writeString(gists_url);
        parcel.writeString(starred_url);
        parcel.writeString(subscriptions_url);
        parcel.writeString(organizations_url);
        parcel.writeString(repos_url);
        parcel.writeString(events_url);
        parcel.writeString(received_events_url);
        parcel.writeString(type);
        parcel.writeByte((byte) (site_admin ? 1 : 0));
        parcel.writeDouble(score);
    }
}
