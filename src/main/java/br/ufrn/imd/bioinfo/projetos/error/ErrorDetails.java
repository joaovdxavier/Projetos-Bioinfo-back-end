package br.ufrn.imd.bioinfo.projetos.error;

public class ErrorDetails {
	
	private String titulo;
	
    private int status;
    
    private String detalhes;
    
    private long timestamp;
    
    private String mensagem;
    
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getDetalhes() {
		return detalhes;
	}
	
	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}
	
	public long getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getMensagem() {
		return mensagem;
	}
	
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
    
    public static final class Builder{
    	private String titulo;
    	
        private int status;
        
        private String detalhes;
        
        private long timestamp;
        
        private String mensagem;
        
 private Builder() { }
        
        public static Builder newBuilder() {
			return new Builder();
		}

		public Builder setTitulo(String titulo) {
			this.titulo = titulo;
			return this;
		}

		public Builder setStatus(int status) {
			this.status = status;
			return this;
		}

		public Builder setDetalhes(String detalhes) {
			this.detalhes = detalhes;
			return this;
		}

		public Builder setTimestamp(long timestamp) {
			this.timestamp = timestamp;
			return this;
		}

		public Builder setMensagem(String mensagem) {
			this.mensagem = mensagem;
			return this;
		}

        public ErrorDetails build(){
        	ErrorDetails errordetail = new ErrorDetails();
        	errordetail.setTitulo(titulo);
        	errordetail.setStatus(status);
        	errordetail.setDetalhes(detalhes);
        	errordetail.setTimestamp(timestamp);
        	errordetail.setMensagem(mensagem);
        	return errordetail;
        }
    	
    }
}
