package de.merten.umgr.backend.bo;


/**
 * Die Rolle, anhand derer die Berechtigunen im Code bestimmt werden. <br>
 * So haben z.B. Benutzer mit der Rolle 'USER' andere Berechtigungen als
 * Benutzer mit der Rolle 'ADMIN'. <br>
 * Rechte hängen nie direkt an Benutzern, sondern immer an Rollen 
 * (RBAC = Role Based Access Control). <p>
 * NOTE: Mapping entweder als eigenes Entity (mit id etc) oder als 
 * <code>@ElementCollection</code> (hier gewählt) <br>
 * {@link http://stackoverflow.com/questions/3152787/persisting-set-of-enums-in-a-many-to-many-unidirectional-mapping}
 */

public enum Role {
    USER,   //Normalo
    ADMIN,  //Auf Mandantenebene
    ROOT;   //Mandantenübergreifend
}
