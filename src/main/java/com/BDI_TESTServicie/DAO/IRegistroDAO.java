package com.BDI_TESTServicie.DAO;

import com.BDI_TESTServicie.JPA.RegistroSistema;
import com.BDI_TESTServicie.JPA.Result;

public interface IRegistroDAO {
    Result AddJPA(RegistroSistema registroSistema);
}
