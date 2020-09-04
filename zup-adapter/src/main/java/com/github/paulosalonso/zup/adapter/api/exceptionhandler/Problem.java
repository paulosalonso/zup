package com.github.paulosalonso.zup.adapter.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * 
 * This class implements the RFC 7807 and extends some attributes
 * 
 * The RFC attributes are 'status', 'title' and 'detail'
 * 
 * https://tools.ietf.org/html/rfc7807
 * 
 * @author Paulo Alonso
 *
 */
@JsonInclude(Include.NON_NULL)
public class Problem {
	
	private Integer status; // RFC 7807
	private String title; // RFC 7807
	private String detail; // RFC 7807
	
	private OffsetDateTime timestamp;
	
	private List<Violation> violations;
	
	public Integer getStatus() {
		return status;
	}

	public String getTitle() {
		return title;
	}

	public String getDetail() {
		return detail;
	}

	public OffsetDateTime getTimestamp() {
		return timestamp;
	}

	public List<Violation> getViolations() {
		return violations;
	}

	public static class Violation {
		
		private String context;
		private String message;
		
		public String getContext() {
			return context;
		}

		public String getMessage() {
			return message;
		}
		
		public static ViolationBuilder of() {
			return new ViolationBuilder();
		}
		
		public static class ViolationBuilder {
			
			private final Violation violation;
			
			private ViolationBuilder() {
				this.violation = new Violation();
			}
			
			public ViolationBuilder context(String context) {
				this.violation.context = context;
				return this;
			}
			
			public ViolationBuilder message(String message) {
				this.violation.message = message;
				return this;
			}
			
			public Violation build() {
				return this.violation;
			}
			
		}
		
	}
	
	public static ProblemBuilder of() {
		return new ProblemBuilder();
	}
	
	public static class ProblemBuilder {
		
		private final Problem problem;
		
		private ProblemBuilder() {
			this.problem = new Problem();
		}
		
		public ProblemBuilder status(Integer status) {
			this.problem.status = status;
			return this;
		}
		
		public ProblemBuilder title(String title) {
			this.problem.title = title;
			return this;
		}
		
		public ProblemBuilder detail(String detail) {
			this.problem.detail = detail;
			return this;
		}
		
		public ProblemBuilder timestamp(OffsetDateTime timestamp) {
			this.problem.timestamp = timestamp;
			return this;
		}
		
		public ProblemBuilder violations(List<Violation> violations) {
			this.problem.violations = violations;
			return this;
		}
		
		public Problem build() {
			return this.problem;
		}
		
	}
	
}
