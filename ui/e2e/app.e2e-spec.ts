import { DealProjectPage } from './app.po';

describe('deal-project App', () => {
  let page: DealProjectPage;

  beforeEach(() => {
    page = new DealProjectPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
