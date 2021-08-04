package com.brito.sistemapedidos.domain.enums;

public enum StatePayment {
	
	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private int code;
	private String description;
	
	private StatePayment(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}
	
	public String getDescription() {
		return description;
	}

	public static StatePayment toEnum(Integer code) {
		
		if (code == null) {
			return null;
		}
		
		for (StatePayment x : StatePayment.values()) {
			if (code.equals(x.getCode())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + code);
	}

}
