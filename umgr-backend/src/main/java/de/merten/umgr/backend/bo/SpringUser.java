package de.merten.umgr.backend.bo;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import de.merten.umgr.backend.service.UserService;

/**
 * Erweitert den User, der im SpringSecurityContext gehalten wird.  <p>
 * Dieser User aggregiert zusätzlich den den umgr user. Somit wird es möglich, aus dem Spring Security Context 
 * auch den eigentlichen Benutzer zu bekommen.
 */
public class SpringUser extends org.springframework.security.core.userdetails.User {
	
	private static final long serialVersionUID = 870509539210739309L;
	
	// Der Benutzer, der im Energiemanager existent ist.
	de.merten.umgr.backend.bo.User umgrUser;
	
	/**
     * Ruft den komplexeren Konstructor mit allen Boolean-Argumenten = {@code true} auf. <p>
     * Somit ist dieser Benutzer aktiv und nicht gesperrt.
     */
	public SpringUser(de.merten.umgr.backend.bo.User umgrUser, List<GrantedAuthority> roles) {
		this(umgrUser,true, true, true, true, roles);
	}
	
	/**
     * Erzeugt einen <code>User</code> wie er von 
     * {@link org.springframework.security.authentication.dao.DaoAuthenticationProvider} benötigt 
     * und vom {@link de.merten.umgr.backend.service.UserService} geliefert wird.
     *
     * @param umgrUser liefert den username und das Passwort für den 
     *        <code>DaoAuthenticationProvider</code> und wird zudem als Referenz gespeichert.
    
     * @param enabled ist <code>true</code> wenn der Benutzer freigeschaltet.
     * @param accountNonExpired ist <code>true</code> wenn der Account nicht abgelaufen ist.
     * @param credentialsNonExpired ist <code>true</code> wenn das Passwort nicht abgelaufen ist.
     * @param accountNonLocked ist <code>true</code> wenn der Account nicht gesperrt ist.
     * @param authorities die Rollen die der korrekt angemeldete und aktive Benutzer hat. Nicht null.
     *
     * @throws IllegalArgumentException wenn der Benutzername des Benutzers <code>null</code> ist oder ein Wert 
     *         der <code>GrantedAuthority</code> collection null ist.
     */
	public SpringUser(de.merten.umgr.backend.bo.User umgrUser, boolean enabled, boolean accountNonExpired,
            boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> roles) {
		super(umgrUser.getLogin(), umgrUser.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, roles);
		this.umgrUser = umgrUser;
	}

	
}
