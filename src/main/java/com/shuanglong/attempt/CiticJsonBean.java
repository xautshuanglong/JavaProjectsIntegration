package com.shuanglong.attempt;
//package com.ebao.frontend.processor.batch.citic.jzcd;

import java.util.Date;

public class CiticJsonBean {
	private TouBaoRen toubaoren;
	private BeiBaoRen beibaoren;

	public CiticJsonBean() {
	}
	
	public CiticJsonBean(TouBaoRen toubaoren, BeiBaoRen beibaoren) {
		this.toubaoren = toubaoren;
		this.beibaoren = beibaoren;
	}

	public TouBaoRen getToubaoren() {
		return toubaoren;
	}

	public void setToubaoren(TouBaoRen toubaoren) {
		this.toubaoren = toubaoren;
	}

	public BeiBaoRen getBeibaoren() {
		return beibaoren;
	}

	public void setBeibaoren(BeiBaoRen beibaoren) {
		this.beibaoren = beibaoren;
	}
	
	
	class TouBaoRen {
		
		private String tbr_name;
		private String tbr_sex;
		private Date tbr_brdt;
		private String tbr_idtp;
		private String tbr_idnm;
		private String tbr_expy_dt;

		public String getTbr_name() {
			return tbr_name;
		}

		public void setTbr_name(String tbr_name) {
			this.tbr_name = tbr_name;
		}

		public String getTbr_sex() {
			return tbr_sex;
		}

		public void setTbr_sex(String tbr_sex) {
			this.tbr_sex = tbr_sex;
		}

		public Date getTbr_brdt() {
			return tbr_brdt;
		}

		public void setTbr_brdt(Date tbr_brdt) {
			this.tbr_brdt = tbr_brdt;
		}

		public String getTbr_idtp() {
			return tbr_idtp;
		}

		public void setTbr_idtp(String tbr_idtp) {
			this.tbr_idtp = tbr_idtp;
		}

		public String getTbr_idnm() {
			return tbr_idnm;
		}

		public void setTbr_idnm(String tbr_idnm) {
			this.tbr_idnm = tbr_idnm;
		}

		public String getTbr_expy_dt() {
			return tbr_expy_dt;
		}

		public void setTbr_expy_dt(String tbr_expy_dt) {
			this.tbr_expy_dt = tbr_expy_dt;
		}
	}

	class BeiBaoRen {
		
		private String bbr_name;
		private String bbr_sex;
		private Date bbr_brdt;
		private String bbr_idtp;
		private String bbr_idnm;
		private String bbr_expy_dt;

		public String getBbr_name() {
			return bbr_name;
		}

		public void setBbr_name(String bbr_name) {
			this.bbr_name = bbr_name;
		}

		public String getBbr_sex() {
			return bbr_sex;
		}

		public void setBbr_sex(String bbr_sex) {
			this.bbr_sex = bbr_sex;
		}

		public Date getBbr_brdt() {
			return bbr_brdt;
		}

		public void setBbr_brdt(Date bbr_brdt) {
			this.bbr_brdt = bbr_brdt;
		}

		public String getBbr_idtp() {
			return bbr_idtp;
		}

		public void setBbr_idtp(String bbr_idtp) {
			this.bbr_idtp = bbr_idtp;
		}

		public String getBbr_idnm() {
			return bbr_idnm;
		}

		public void setBbr_idnm(String bbr_idnm) {
			this.bbr_idnm = bbr_idnm;
		}

		public String getBbr_expy_dt() {
			return bbr_expy_dt;
		}

		public void setBbr_expy_dt(String bbr_expy_dt) {
			this.bbr_expy_dt = bbr_expy_dt;
		}
	}
}
