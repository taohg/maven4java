package mavenTest;

import java.util.Date;

public class BiddInfo{
    public Long busiOptnyId;

    private String biddAnoceNm;

    private String biddTypeCd;

    private String tenderProjNm;

    private Date biddInfoIssueTime;

    private Date tenderDueTime;

    private String amt;

    private String tenderModeName;

    private String tenderDtldCntt;

    private String tenderDtldCnttRich;

    private String srcChnlId;

    private Date gatherTime;

    private String orgDataSrc;

    private String srcWebsiteAddr;

    private String projNo;

    private String abst;

    private String biddProduct;

    private String biddState;

    private String belgProvCode;

    private String belgCityCode;

    private String belgDistrtCode;

    private String indstCd;

    private String indstBigClaCd;

    private String indstMidCd;

    private String indstSmallClaCd;

    private Date syncTime;

    private String tagCode;

    private String tagName;

    private Date enrollEndTime;

    private String tendererNm;

    public BiddInfo(Long busiOptnyId, String biddAnoceNm, String biddTypeCd, String tenderProjNm, Date biddInfoIssueTime, Date tenderDueTime, String amt, String tenderModeName, String tenderDtldCntt, String tenderDtldCnttRich, String srcChnlId, Date gatherTime, String orgDataSrc, String srcWebsiteAddr, String projNo, String abst, String biddProduct, String biddState, String belgProvCode, String belgCityCode, String belgDistrtCode, String indstCd, String indstBigClaCd, String indstMidCd, String indstSmallClaCd, Date syncTime, String tagCode, String tagName, Date enrollEndTime, String tendererNm) {
        this.busiOptnyId = busiOptnyId;
        this.biddAnoceNm = biddAnoceNm;
        this.biddTypeCd = biddTypeCd;
        this.tenderProjNm = tenderProjNm;
        this.biddInfoIssueTime = biddInfoIssueTime;
        this.tenderDueTime = tenderDueTime;
        this.amt = amt;
        this.tenderModeName = tenderModeName;
        this.tenderDtldCntt = tenderDtldCntt;
        this.tenderDtldCnttRich = tenderDtldCnttRich;
        this.srcChnlId = srcChnlId;
        this.gatherTime = gatherTime;
        this.orgDataSrc = orgDataSrc;
        this.srcWebsiteAddr = srcWebsiteAddr;
        this.projNo = projNo;
        this.abst = abst;
        this.biddProduct = biddProduct;
        this.biddState = biddState;
        this.belgProvCode = belgProvCode;
        this.belgCityCode = belgCityCode;
        this.belgDistrtCode = belgDistrtCode;
        this.indstCd = indstCd;
        this.indstBigClaCd = indstBigClaCd;
        this.indstMidCd = indstMidCd;
        this.indstSmallClaCd = indstSmallClaCd;
        this.syncTime = syncTime;
        this.tagCode = tagCode;
        this.tagName = tagName;
        this.enrollEndTime = enrollEndTime;
        this.tendererNm = tendererNm;
    }

    public BiddInfo() {
        super();
    }

    public Long getBusiOptnyId() {
        return busiOptnyId;
    }

    public void setBusiOptnyId(Long busiOptnyId) {
        this.busiOptnyId = busiOptnyId;
    }

    public String getBiddAnoceNm() {
        return biddAnoceNm;
    }

    public void setBiddAnoceNm(String biddAnoceNm) {
        this.biddAnoceNm = biddAnoceNm == null ? null : biddAnoceNm.trim();
    }

    public String getBiddTypeCd() {
        return biddTypeCd;
    }

    public void setBiddTypeCd(String biddTypeCd) {
        this.biddTypeCd = biddTypeCd == null ? null : biddTypeCd.trim();
    }

    public String getTenderProjNm() {
        return tenderProjNm;
    }

    public void setTenderProjNm(String tenderProjNm) {
        this.tenderProjNm = tenderProjNm == null ? null : tenderProjNm.trim();
    }

    public Date getBiddInfoIssueTime() {
        return biddInfoIssueTime;
    }

    public void setBiddInfoIssueTime(Date biddInfoIssueTime) {
        this.biddInfoIssueTime = biddInfoIssueTime;
    }

    public Date getTenderDueTime() {
        return tenderDueTime;
    }

    public void setTenderDueTime(Date tenderDueTime) {
        this.tenderDueTime = tenderDueTime;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt == null ? null : amt.trim();
    }

    public String getTenderModeName() {
        return tenderModeName;
    }

    public void setTenderModeName(String tenderModeName) {
        this.tenderModeName = tenderModeName;
    }

    public String getTenderDtldCntt() {
        return tenderDtldCntt;
    }

    public void setTenderDtldCntt(String tenderDtldCntt) {
        this.tenderDtldCntt = tenderDtldCntt == null ? null : tenderDtldCntt.trim();
    }

    public String getTenderDtldCnttRich() {
        return tenderDtldCnttRich;
    }

    public void setTenderDtldCnttRich(String tenderDtldCnttRich) {
        this.tenderDtldCnttRich = tenderDtldCnttRich == null ? null : tenderDtldCnttRich.trim();
    }

    public String getSrcChnlId() {
        return srcChnlId;
    }

    public void setSrcChnlId(String srcChnlId) {
        this.srcChnlId = srcChnlId == null ? null : srcChnlId.trim();
    }

    public Date getGatherTime() {
        return gatherTime;
    }

    public void setGatherTime(Date gatherTime) {
        this.gatherTime = gatherTime;
    }

    public String getOrgDataSrc() {
        return orgDataSrc;
    }

    public void setOrgDataSrc(String orgDataSrc) {
        this.orgDataSrc = orgDataSrc == null ? null : orgDataSrc.trim();
    }

    public String getSrcWebsiteAddr() {
        return srcWebsiteAddr;
    }

    public void setSrcWebsiteAddr(String srcWebsiteAddr) {
        this.srcWebsiteAddr = srcWebsiteAddr == null ? null : srcWebsiteAddr.trim();
    }

    public String getProjNo() {
        return projNo;
    }

    public void setProjNo(String projNo) {
        this.projNo = projNo == null ? null : projNo.trim();
    }

    public String getAbst() {
        return abst;
    }

    public void setAbst(String abst) {
        this.abst = abst == null ? null : abst.trim();
    }

    public String getBiddProduct() {
        return biddProduct;
    }

    public void setBiddProduct(String biddProduct) {
        this.biddProduct = biddProduct == null ? null : biddProduct.trim();
    }

    public String getBiddState() {
        return biddState;
    }

    public void setBiddState(String biddState) {
        this.biddState = biddState == null ? null : biddState.trim();
    }

    public String getBelgProvCode() {
        return belgProvCode;
    }

    public void setBelgProvCode(String belgProvCode) {
        this.belgProvCode = belgProvCode == null ? null : belgProvCode.trim();
    }

    public String getBelgCityCode() {
        return belgCityCode;
    }

    public void setBelgCityCode(String belgCityCode) {
        this.belgCityCode = belgCityCode == null ? null : belgCityCode.trim();
    }

    public String getBelgDistrtCode() {
    	return belgDistrtCode;
    }
    
    public void setBelgDistrtCode(String belgDistrtCode) {
    	this.belgDistrtCode = belgDistrtCode == null ? null : belgDistrtCode.trim();
    }
    
    public String getIndstCd() {
        return indstCd;
    }

    public void setIndstCd(String indstCd) {
        this.indstCd = indstCd == null ? null : indstCd.trim();
    }

    public String getIndstBigClaCd() {
        return indstBigClaCd;
    }

    public void setIndstBigClaCd(String indstBigClaCd) {
        this.indstBigClaCd = indstBigClaCd == null ? null : indstBigClaCd.trim();
    }

    public String getIndstMidCd() {
        return indstMidCd;
    }

    public void setIndstMidCd(String indstMidCd) {
        this.indstMidCd = indstMidCd == null ? null : indstMidCd.trim();
    }

    public String getIndstSmallClaCd() {
        return indstSmallClaCd;
    }

    public void setIndstSmallClaCd(String indstSmallClaCd) {
        this.indstSmallClaCd = indstSmallClaCd == null ? null : indstSmallClaCd.trim();
    }

    public Date getSyncTime() {
        return syncTime;
    }

    public void setSyncTime(Date syncTime) {
        this.syncTime = syncTime;
    }

    public String getTagCode() {
        return tagCode;
    }

    public void setTagCode(String tagCode) {
        this.tagCode = tagCode == null ? null : tagCode.trim();
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName == null ? null : tagName.trim();
    }

    public Date getEnrollEndTime() {
        return enrollEndTime;
    }

    public void setEnrollEndTime(Date enrollEndTime) {
        this.enrollEndTime = enrollEndTime;
    }

    public String getTendererNm() {
        return tendererNm;
    }

    public void setTendererNm(String tendererNm) {
        this.tendererNm = tendererNm == null ? null : tendererNm.trim();
    }
}