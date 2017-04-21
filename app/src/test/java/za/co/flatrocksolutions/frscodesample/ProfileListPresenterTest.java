package za.co.flatrocksolutions.frscodesample;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import za.co.flatrocksolutions.frscodesample.di.DaggerTestComponent;
import za.co.flatrocksolutions.frscodesample.di.TestComponent;
import za.co.flatrocksolutions.frscodesample.interactor.UserProfileInteractor;
import za.co.flatrocksolutions.frscodesample.model.UserProfile;
import za.co.flatrocksolutions.frscodesample.profile_list.contract.ProfileListContract;
import za.co.flatrocksolutions.frscodesample.profile_list.presenter.ProfileListPresenter;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Created by renier on 4/20/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ProfileListPresenterTest {
    @Rule public MockitoRule mockito = MockitoJUnit.rule();

    @Inject
    ProfileListContract.View mView;

    @Inject
    UserProfileInteractor mInteractor;

    @Inject
    ProfileListPresenter mPresenter;

    @Before
    public void before() {
        TestComponent component = DaggerTestComponent.builder().build();
        component.inject(this);
    }

    @Test
    public void verifyNothingCalledAfterViewSet() {
        mPresenter.setView(mView);

        verifyZeroInteractions(mView);
        verifyZeroInteractions(mInteractor);
    }

    @Test
    public void verifyNothingCalledAfterUnsubscribe() {
        mPresenter.onUnsubscribe();

        verifyZeroInteractions(mView);
        verifyZeroInteractions(mInteractor);
    }

    @Test
    public void verifyInteractorCalledAfterOnSubscribe() {
        mPresenter.setView(mView);

        ArrayList<UserProfile> list = new ArrayList<>();
        when(mInteractor.getListOfUsers()).thenReturn(Observable.create(subscriber -> {
            subscriber.onNext(list);
            subscriber.onComplete();
        }));
        mPresenter.onSubscribe();

        verify(mInteractor).getListOfUsers();
    }

    @Test
    public void verifyProgressBarShownAfterOnSubscribe() {
        mPresenter.setView(mView);

        ArrayList<UserProfile> list = new ArrayList<>();
        when(mInteractor.getListOfUsers()).thenReturn(Observable.just(list));

        mPresenter.setView(mView);
        mPresenter.onSubscribe();

        verify(mView).showProgressBar();
    }

    @Test
    public void verifyShowNoUsersIfListIsEmpty() {
        mPresenter.setView(mView);

        ArrayList<UserProfile> list = new ArrayList<>();
        when(mInteractor.getListOfUsers()).thenReturn(Observable.just(list));

        mPresenter.setView(mView);
        mPresenter.onSubscribe();

        verify(mView).noUsersToShow();
    }

    @Test
    public void verifyShowUsersIfListHasUsers() {
        mPresenter.setView(mView);

        ArrayList<UserProfile> list = new ArrayList<>();
        list.add(Mockito.mock(UserProfile.class));
        list.add(Mockito.mock(UserProfile.class));
        list.add(Mockito.mock(UserProfile.class));

        when(mInteractor.getListOfUsers()).thenReturn(Observable.just(list));

        mPresenter.setView(mView);
        mPresenter.onSubscribe();

        verify(mView).setUsersToShow(list);
    }

    @Test
    public void verifyErrorShownAfterOnSubscribeWithErrorOnInteractor() {
        mPresenter.setView(mView);

        when(mInteractor.getListOfUsers()).thenReturn(Observable.error(new Exception()));

        mPresenter.setView(mView);
        mPresenter.onSubscribe();

        verify(mView).onError();
    }

    @Test
    public void verifyProgressBarHiddenAfterSuccessfulCallWithNoUsers() {
        mPresenter.setView(mView);

        ArrayList<UserProfile> list = new ArrayList<>();
        when(mInteractor.getListOfUsers()).thenReturn(Observable.just(list));

        mPresenter.setView(mView);
        mPresenter.onSubscribe();

        verify(mView).hideProgressBar();
    }

    @Test
    public void verifyProgressBarHiddenAfterSuccessfulCallWithUsers() {
        mPresenter.setView(mView);

        ArrayList<UserProfile> list = new ArrayList<>();
        list.add(Mockito.mock(UserProfile.class));
        list.add(Mockito.mock(UserProfile.class));
        list.add(Mockito.mock(UserProfile.class));

        when(mInteractor.getListOfUsers()).thenReturn(Observable.just(list));

        mPresenter.setView(mView);
        mPresenter.onSubscribe();

        verify(mView).hideProgressBar();
    }

    @Test
    public void verifyProgressBarHiddenAfterErrorCall() {
        mPresenter.setView(mView);

        when(mInteractor.getListOfUsers()).thenReturn(Observable.error(new Exception()));

        mPresenter.setView(mView);
        mPresenter.onSubscribe();

        verify(mView).hideProgressBar();
    }

    @Test
    public void verifyLaunchProfilePageCalledWhenClickingOnUser() {
        mPresenter.setView(mView);

        UserProfile profile = Mockito.mock(UserProfile.class);
        mPresenter.profileClicked(profile);

        verify(mView).launchUserProfileDetail(profile);
    }
}
