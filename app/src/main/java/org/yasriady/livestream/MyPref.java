package org.yasriady.livestream;

import org.yasriady.livestream.BaseClasses.PrefBase;

/**
 * Created by dedy on 11/14/17.
 */

public class MyPref extends PrefBase {

    private final String SHOLAT_NAME = "SholatName";
    private final String TIME_OFFSET = "TimeOffset";
    private final String IQOMAH_DURATION = "IqomahDuration";
    private final String PLAY_ADZAN = "PlayAdzan";
    private final String ADZAN_FILENAME = "AdzanFilename";
    private final String PLAY_IQOMAH = "PlayIqomah";
    private final String IQOMAH_FILENAME = "IqomahFilename";

    private String m_keyPrefix;
    private String adzanFilename;

    public MyPref(MyDB db/*, final String keyPrefix*/) {
        super(db);
        //this.m_keyPrefix = keyPrefix;
    }

    /*
     * General preferences
     */
    public final String getMasjidName() {
        final String result = get("MasjidName", "Nama Masjid");
        return result;
    }

    public void setMasjidName(final String value) {
        set("MasjidName", value);
    }

    public final String getMasjidAddress() {
        final String result = get("Address", "Alamat Masjid");
        return result;
    }

    public void setMasjidAddress(final String value) {
        set("Address", value);
    }

    public final String getCity() {
        final String result = get("City", "Custom");
        return result;
    }

    public void setCity(final String value) {
        set("City", value);
    }

    public final double getMasjidLongitude() {
        final double result = get("Longitude", 0);
        return result;
    }

    public void setMasjidLongitude(final double value) {
        set("Longitude", value);
    }

    public final double getMasjidLatitude() {
        final double result = get("Latitude", 0);
        return result;
    }

    public void setMasjidLatitude(final double value) {
        set("Latitude", value);
    }

    public final double getMasjidAltitude() {
        final double result = get("MasjidAltitude", 0);
        return result;
    }

    public void setMasjidAltitude(final double value) {
        set("Altitude", value);
    }

    //getTimezone
    //setTimezone

    public MyPref setKeyPrefix(final String keyPrefix) {
        this.m_keyPrefix = keyPrefix;
        return this;
    }

    private String keyPrefix() {
        return m_keyPrefix + "_";
    }

    public String getSholatName(final String defValue) {
        final String key = keyPrefix() + SHOLAT_NAME;
        final String result = get(key, defValue);
        return result;
    }

    public void setSholatName(final String name) {
        set(keyPrefix() + SHOLAT_NAME, name);
    }

    public final double getTimeOffset() {
        final String key = keyPrefix() + TIME_OFFSET;
        final double result = get(key, 0);
        return result;
    }

    public final double getIqomahDuration() {
        final String key = keyPrefix() + IQOMAH_DURATION;
        final double result = get(key, 10);
        return result;
    }

    public final boolean getPlayAdzan() {
        final String key = keyPrefix() + PLAY_ADZAN;
        final double val = get(key, 1);
        boolean result = val == 1;
        return result;
    }

    public String getAdzanFilename() {
        final String key = keyPrefix() + ADZAN_FILENAME;
        final String result = get(key, "default_adzan.mp3");
        return result;
    }

    public final boolean getPlayIqomah() {
        final String key = keyPrefix() + PLAY_IQOMAH;
        final double val = get(key, 1);
        boolean result = val == 1;
        return result;
    }

    public String getIqomahFilename() {
        final String key = keyPrefix() + IQOMAH_FILENAME;
        final String result = get(key, "default_iqomah.mp3");
        return result;
    }

}
