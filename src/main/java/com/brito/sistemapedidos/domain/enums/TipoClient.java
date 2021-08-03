package com.brito.sistemapedidos.domain.enums;

public enum TipoClient {
	
	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
	private int code;
	private String description;
	
	private TipoClient(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	
	public String getDescription() {
		return description;
	}

	public static TipoClient toEnum(Integer code) {
		
		if (code == null) {
			return null;
		}
		
		for (TipoClient x : TipoClient.values()) {
			if (code.equals(x.getCode())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + code);
	}
	
	

}
