package controller.jspused;

import java.util.List;

import dao.tables.UseRecords;

public class OneDayInfo {
    private boolean success;
    private UseRecords r0;
    private UseRecords r6;
    private UseRecords r22;
    private UseRecords n0;
    private Integer lowUsed;
    private Integer highUsed;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public UseRecords getR0() {
        return r0;
    }

    public void setR0(UseRecords r0) {
        this.r0 = r0;
    }

    public UseRecords getR6() {
        return r6;
    }

    public void setR6(UseRecords r6) {
        this.r6 = r6;
    }

    public UseRecords getR22() {
        return r22;
    }

    public void setR22(UseRecords r22) {
        this.r22 = r22;
    }

    public UseRecords getN0() {
        return n0;
    }

    public void setN0(UseRecords n0) {
        this.n0 = n0;
    }

    public Integer getLowUsed() {
        return lowUsed;
    }

    public void setLowUsed(Integer lowUsed) {
        this.lowUsed = lowUsed;
    }

    public Integer getHighUsed() {
        return highUsed;
    }

    public void setHighUsed(Integer highUsed) {
        this.highUsed = highUsed;
    }

    @Override
    public String toString() {
        return "OneDayInfo [success=" + success + ", r0=" + r0 + ", r6=" + r6 + ", r22=" + r22 + ", n0=" + n0
                + ", lowUsed=" + lowUsed + ", highUsed=" + highUsed + "]";
    }

}
