import { KAagular2PocPage } from './app.po';

describe('kaagular2-poc App', () => {
  let page: KAagular2PocPage;

  beforeEach(() => {
    page = new KAagular2PocPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
