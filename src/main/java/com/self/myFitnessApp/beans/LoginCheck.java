package com.self.myFitnessApp.beans;

import ch.qos.logback.core.net.server.Client;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginCheck {
    private boolean isLogin;
    private ClientType clientType;
}
