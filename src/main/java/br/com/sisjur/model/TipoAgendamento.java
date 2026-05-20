package br.com.sisjur.model;

   // Usei Enum aqui pra travar as opções. Se eu deixasse como texto livre, 
   // ia virar uma bagunça de erro de digitação no banco, então preferi 
   // controlar tudo por aqui mesmo.

public enum TipoAgendamento {
    CONSULTA,
    TRIAGEM,
    AUDIENCIA,
    REUNIAO,
    OUTRO
}
