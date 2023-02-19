package it.auties.whatsapp.model.info;

import it.auties.protobuf.base.ProtobufName;
import it.auties.protobuf.base.ProtobufProperty;
import it.auties.whatsapp.model.business.BusinessActorsType;
import it.auties.whatsapp.model.business.BusinessStorageType;
import it.auties.whatsapp.model.business.BusinessVerifiedLevel;
import it.auties.whatsapp.model.business.BusinessVerifiedNameCertificate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.jackson.Jacksonized;

import static it.auties.protobuf.base.ProtobufType.*;

/**
 * A model class that holds the information related to the identity of a business account.
 */
@AllArgsConstructor
@Data
@Builder
@Jacksonized
@Accessors(fluent = true)
@ProtobufName("BizIdentityInfo")
public final class BusinessIdentityInfo implements Info {
    /**
     * The level of verification of this account
     */
    @ProtobufProperty(index = 1, type = MESSAGE, implementation = BusinessVerifiedLevel.class)
    private BusinessVerifiedLevel level;

    /**
     * The certificate of this account
     */
    @ProtobufProperty(index = 2, type = MESSAGE, implementation = BusinessVerifiedNameCertificate.class)
    private BusinessVerifiedNameCertificate certificate;

    /**
     * Indicates whether this account has a signed certificate
     */
    @ProtobufProperty(index = 3, type = BOOL)
    private boolean signed;

    /**
     * Indicates whether the signed certificate of this account has been revoked
     */
    @ProtobufProperty(index = 4, type = BOOL)
    private boolean revoked;

    /**
     * Indicates where this account is hosted
     */
    @ProtobufProperty(index = 5, type = MESSAGE, implementation = BusinessStorageType.class)
    private BusinessStorageType hostStorage;

    /**
     * The actual actors of this account
     */
    @ProtobufProperty(index = 6, type = MESSAGE, implementation = BusinessActorsType.class)
    private BusinessActorsType actualActors;

    /**
     * The privacy mode of this account
     */
    @ProtobufProperty(index = 7, type = UINT64)
    private long privacyModeTs;

    /**
     * Feature controls
     */
    @ProtobufProperty(index = 8, type = UINT64)
    private long featureControls;
}