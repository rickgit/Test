package edu.ptu.androidtest.di.dagger2.scope;

import javax.inject.Scope;
// 无Scope的Component不能依赖有Scope的Component，因为这也会导致Scope被破坏。 提示(unscoped) cannot depend on scoped components
@Scope
public @interface DFragmentScope {
}
