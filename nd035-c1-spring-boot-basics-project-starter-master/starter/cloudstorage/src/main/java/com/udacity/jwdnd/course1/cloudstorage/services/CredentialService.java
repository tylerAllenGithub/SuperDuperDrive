package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CredentialService {

    private CredentialMapper credentialMapper;

    public CredentialService(CredentialMapper credentialMapper)
    {
        this.credentialMapper = credentialMapper;
    }

    public Credential getCredential(Integer credentialId){
        return credentialMapper.getCredential(credentialId);
    }
    public List<Credential> getCredentials(Integer userid){
        return credentialMapper.getCredentials(userid);
    }
    public void addCredential(Credential credential){
        credentialMapper.insert(credential);
    }
    public void editCredential(Credential credential){
        credentialMapper.update(credential);
    }
    public void deleteCredential(Integer credentialid){
        credentialMapper.delete(credentialid);
    }

}
